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
    public String updateDoctor(@RequestBody RequestDoctorDto doctorDto, @RequestParam String id){
        return "Updated : "+doctorDto.toString();
    }
    @DeleteMapping("{id}")
    public String deleteDoctor(@PathVariable String id){
        return id+" deleted......";
    }
    @GetMapping(path = "/list",params = {"searchText","page","size"})
    public String getAllDoctors(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){
        return "Doctor List";
    }
}
