package com.hygienetracker.backend.dto;

import com.hygienetracker.backend.model.User;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private User.Role role;
    private String wardNumber;
}