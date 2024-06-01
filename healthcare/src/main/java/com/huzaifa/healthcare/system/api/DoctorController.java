package com.huzaifa.healthcare.system.api;

import com.huzaifa.healthcare.system.dto.request.RequestDoctorDto;
import com.huzaifa.healthcare.system.service.DoctorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public String createDoctor(@RequestBody RequestDoctorDto doctorDto){
        doctorService.createDoctor(doctorDto);
        return "Dr. "+doctorDto.getName()+" saved....";
    }
    @GetMapping("/{id}")
    public String findDoctor(@PathVariable String id){
        return id+" ";
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
