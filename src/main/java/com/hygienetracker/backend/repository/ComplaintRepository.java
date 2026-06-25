package com.hygienetracker.backend.repository;

import com.hygienetracker.backend.model.Complaint;
import com.hygienetracker.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByCitizen(User citizen);
    List<Complaint> findByWardNumber(String wardNumber);
    List<Complaint> findByAssignedOfficial(User official);
}