package com.hygienetracker.backend.service;

import com.hygienetracker.backend.dto.ProfileUpdateRequest;
import com.hygienetracker.backend.model.User;
import com.hygienetracker.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getProfile(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateProfile(String email, ProfileUpdateRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getName() != null) user.setName(request.getName());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getArea() != null) user.setArea(request.getArea());
        if (request.getDistrict() != null) user.setDistrict(request.getDistrict());
        if (request.getState() != null) user.setState(request.getState());
        if (request.getWardNumber() != null) user.setWardNumber(request.getWardNumber());
        if (request.getAadhaar() != null) user.setAadhaar(request.getAadhaar());
        if (request.getAge() != null) user.setAge(request.getAge());
        if (request.getGender() != null) user.setGender(request.getGender());
        if (request.getAddress() != null) user.setAddress(request.getAddress());

        return userRepository.save(user);
    }
}