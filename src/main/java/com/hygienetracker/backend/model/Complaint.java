package com.hygienetracker.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String wardNumber;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private User citizen;

    @ManyToOne
    @JoinColumn(name = "assigned_official_id")
    private User assignedOfficial;

    private String assignedCrew;

    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        status = Status.PENDING;
    }

    public enum Status {
        PENDING, ASSIGNED, RESOLVED
    }
}