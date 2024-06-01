package com.huzaifa.healthcare.system.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ResponseDoctorDto {
    private long id;
    private String name;
    private String address;
    private String contact;
    private double salary;
}
