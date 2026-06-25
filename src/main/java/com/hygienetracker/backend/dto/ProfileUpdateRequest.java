package com.hygienetracker.backend.dto;

import lombok.Data;

@Data
public class ProfileUpdateRequest {
    private String name;
    private String phone;
    private String area;
    private String district;
    private String state;
    private String wardNumber;
    private String address;
    private String aadhaar;
    private Integer age;
    private String gender;

}