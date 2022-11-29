package com.example.databaseproject.dto.user.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class CreateUserRequestDto {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String job;
    private Date birthday;
    private String socialNumber;
}
