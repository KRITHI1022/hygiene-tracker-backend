package com.hygienetracker.backend.controller;

import com.hygienetracker.backend.dto.AssignCrewRequest;
import com.hygienetracker.backend.dto.ComplaintRequest;
import com.hygienetracker.backend.model.Complaint;
import com.hygienetracker.backend.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    // Citizen - raise complaint
    @PostMapping
    public ResponseEntity<Complaint> raiseComplaint(
            @RequestBody ComplaintRequest request,
            Principal principal) {
        return ResponseEntity.ok(
                complaintService.raiseComplaint(request, principal.getName()));
    }

    // Citizen - view own complaints
    @GetMapping("/my")
    public ResponseEntity<List<Complaint>> getMyComplaints(Principal principal) {
        return ResponseEntity.ok(
                complaintService.getMyComplaints(principal.getName()));
    }

    // Official - view complaints in their ward
    @GetMapping("/ward")
    public ResponseEntity<List<Complaint>> getWardComplaints(Principal principal) {
        return ResponseEntity.ok(
                complaintService.getComplaintsByWard(principal.getName()));
    }

    // Official - assign crew
    @PutMapping("/{id}/assign")
    public ResponseEntity<Complaint> assignCrew(
            @PathVariable Long id,
            @RequestBody AssignCrewRequest request) {
        return ResponseEntity.ok(complaintService.assignCrew(id, request));
    }

    // Official - resolve complaint
    @PutMapping("/{id}/resolve")
    public ResponseEntity<Complaint> resolveComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.resolveComplaint(id));
    }
}