package com.huzaifa.healthcare.system.service;

import com.huzaifa.healthcare.system.dto.request.RequestDoctorDto;
import com.huzaifa.healthcare.system.dto.response.ResponseDoctorDto;

import java.util.List;

public interface DoctorService {
    public void createDoctor(RequestDoctorDto doctorDto);
    public ResponseDoctorDto getDoctor(long id);
    public void deleteDoctor(long id);
    public void updateDoctor(long id, RequestDoctorDto doctorDto);
    public List<ResponseDoctorDto> gelAllDoctors(String searchText, int page, int size);
}
