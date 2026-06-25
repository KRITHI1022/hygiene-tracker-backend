package com.hygienetracker.backend.repository;

import com.hygienetracker.backend.model.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BinRepository extends JpaRepository<Bin, Long> {
    List<Bin> findByWardNumber(String wardNumber);
}