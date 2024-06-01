package com.huzaifa.healthcare.system.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data

public class RequestDoctorDto {
    private String name;
    private String address;
    private String contact;
    private double salary;
}
