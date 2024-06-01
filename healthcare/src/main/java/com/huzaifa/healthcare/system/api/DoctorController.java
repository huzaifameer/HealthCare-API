package com.huzaifa.healthcare.system.api;

import com.huzaifa.healthcare.system.dto.request.RequestDoctorDto;
import com.huzaifa.healthcare.system.service.DoctorService;
import com.huzaifa.healthcare.system.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<StandardResponse> createDoctor(@RequestBody RequestDoctorDto doctorDto){
        doctorService.createDoctor(doctorDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "Doctor saved successfully..!",
                        doctorDto.getName()
                ),
                HttpStatus.CREATED
        );

    }
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findDoctor(@PathVariable long id){
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Doctor Data : ",
                        doctorService.getDoctor(id)
                ),
                HttpStatus.OK
        );
    }
    @PutMapping(params = "id")
    public ResponseEntity<StandardResponse> updateDoctor(@RequestBody RequestDoctorDto doctorDto, @RequestParam long id){
        doctorService.updateDoctor(id, doctorDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Updated Doctor Data : ",
                        doctorService.getDoctor(id)
                ),
                HttpStatus.CREATED
        );
    }
    @DeleteMapping("{id}")
    public ResponseEntity<StandardResponse> deleteDoctor(@PathVariable long id){
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        204,
                        "Doctor Deleted !",
                        doctorService.getDoctor(id).getName()
                ),
                HttpStatus.NO_CONTENT
        );
    }
    @GetMapping(path = "/list",params = {"searchText","page","size"})
    public ResponseEntity<StandardResponse> getAllDoctors(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "Doctor List !",
                        doctorService.getAllDoctors(searchText,page,size)
                ),
                HttpStatus.OK
        );
    }
}
