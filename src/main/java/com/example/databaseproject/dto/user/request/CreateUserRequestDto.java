package com.example.databaseproject.dto.user.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class CreateUserRequestDto {
    private String name;            // 고객 이름
    private String address;         // 고객 주소
    private String phone;           // 고객 전화번호
    private String email;           // 고객 이메일
    private String job;             // 고객 직업
    private Date birthday;          // 고객 생년월일
    private String socialNumber;    // 고객 주민번호
}
