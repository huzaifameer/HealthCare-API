package com.huzaifa.healthcare.system.dto.response;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDoctorDto {
    private long id;
    private String name;
    private String address;
    private String contact;
    private double salary;
}
