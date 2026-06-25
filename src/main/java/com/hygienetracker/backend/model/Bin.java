package com.hygienetracker.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bins")
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String binName;
    private String locationName;
    private String wardNumber;
    private Double latitude;
    private Double longitude;
    private Integer fillLevel;
    private LocalDateTime lastUpdated;

    @PrePersist
    public void prePersist() {
        if (fillLevel == null) fillLevel = 0;
        lastUpdated = LocalDateTime.now();
    }
}