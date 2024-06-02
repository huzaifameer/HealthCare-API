package com.huzaifa.healthcare.system.service.impl;

import com.huzaifa.healthcare.system.dto.request.RequestDoctorDto;
import com.huzaifa.healthcare.system.dto.response.ResponseDoctorDto;
import com.huzaifa.healthcare.system.dto.response.paginated.PaginatedDoctorResponseDto;
import com.huzaifa.healthcare.system.entity.Doctor;
import com.huzaifa.healthcare.system.exceptions.EntryNotFoundException;
import com.huzaifa.healthcare.system.repo.DoctorRepo;
import com.huzaifa.healthcare.system.service.DoctorService;
import com.huzaifa.healthcare.system.util.mapper.DoctorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo, DoctorMapper doctorMapper) {
        this.doctorRepo = doctorRepo;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public void createDoctor(RequestDoctorDto doctorDto) {

        UUID uuid = UUID.randomUUID();
        long docId = uuid.getMostSignificantBits();

        Doctor doctor = new Doctor(
                docId,
                doctorDto.getName(),
                doctorDto.getAddress(),
                doctorDto.getContact(),
                doctorDto.getSalary()
        );

        doctorRepo.save(doctor);
        log.info("Doctor "+doctor.getName()+" saved.");
    }

    @Override
    public ResponseDoctorDto getDoctor(long id) {
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if (selectedDoctor.isEmpty()){
            throw new EntryNotFoundException("Doctor not found !");
        }
        //Doctor doc= selectedDoctor.get();
        return doctorMapper.toResponseDoctorDto(selectedDoctor.get());
        /*return new ResponseDoctorDto(
            doc.getId(),doc.getName(),doc.getAddress(),doc.getContact(),doc.getSalary()
        );*/
    }

    @Override
    public List<ResponseDoctorDto> findDocByName(String name) {
        List<Doctor> allByName = doctorRepo.findAllByName(name);
        return null;
    }

    @Override
    public void deleteDoctor(long id) {
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if (selectedDoctor.isEmpty()){
            throw new EntryNotFoundException("Doctor not found !");
        }
        doctorRepo.deleteById(selectedDoctor.get().getId());
        log.info("Doctor details deleted.....");
    }

    @Override
    public void updateDoctor(long id, RequestDoctorDto doctorDto) {
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if (selectedDoctor.isEmpty()){
            throw new EntryNotFoundException("Doctor not found !");
        }
        Doctor doc = selectedDoctor.get();
        doc.setName(doctorDto.getName());
        doc.setAddress(doctorDto.getAddress());
        doc.setContact(doctorDto.getContact());
        doc.setSalary(doctorDto.getSalary());

        doctorRepo.save(doc);
    }

    @Override
    public PaginatedDoctorResponseDto getAllDoctors(String searchText, int page, int size) {
        searchText = "%"+searchText+"%";
        List<Doctor> doctors = doctorRepo.searchDoctors(searchText, PageRequest.of(page, size));
        long doctorCount = doctorRepo.countDoctors(searchText);
        List<ResponseDoctorDto> doctorDtos = doctorMapper.toResponseDoctorDtoList(doctors);
        /*doctors.forEach(doc -> {
            doctorDtos.add(

                    new ResponseDoctorDto(
                            doc.getId(),doc.getName(),doc.getAddress(),doc.getContact(),doc.getSalary()
                    )
            );
        });*/
        return new PaginatedDoctorResponseDto(
                doctorCount,
                doctorDtos
        );
    }
}
