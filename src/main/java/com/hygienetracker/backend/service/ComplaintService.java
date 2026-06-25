package com.hygienetracker.backend.service;

import com.hygienetracker.backend.dto.AssignCrewRequest;
import com.hygienetracker.backend.dto.ComplaintRequest;
import com.hygienetracker.backend.model.Complaint;
import com.hygienetracker.backend.model.User;
import com.hygienetracker.backend.repository.ComplaintRepository;
import com.hygienetracker.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    public Complaint raiseComplaint(ComplaintRequest request, String email) {
        User citizen = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setCategory(request.getCategory());
        complaint.setPhotoUrl(request.getPhotoUrl());
        complaint.setLatitude(request.getLatitude());
        complaint.setLongitude(request.getLongitude());
        complaint.setWardNumber(citizen.getWardNumber());
        complaint.setCitizen(citizen);

        return complaintRepository.save(complaint);
    }

    public List<Complaint> getMyComplaints(String email) {
        User citizen = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return complaintRepository.findByCitizen(citizen);
    }

    public List<Complaint> getComplaintsByWard(String email) {
        User official = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return complaintRepository.findByWardNumber(official.getWardNumber());
    }

    public Complaint assignCrew(Long complaintId, AssignCrewRequest request) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        User official = userRepository.findById(request.getOfficialId())
                .orElseThrow(() -> new RuntimeException("Official not found"));

        complaint.setAssignedCrew(request.getCrewName());
        complaint.setAssignedOfficial(official);
        complaint.setStatus(Complaint.Status.ASSIGNED);

        return complaintRepository.save(complaint);
    }

    public Complaint resolveComplaint(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setStatus(Complaint.Status.RESOLVED);
        complaint.setResolvedAt(LocalDateTime.now());

        return complaintRepository.save(complaint);
    }
}