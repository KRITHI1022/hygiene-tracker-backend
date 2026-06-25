package com.hygienetracker.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String message;
    private String type;
    private Boolean isRead;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        isRead = false;
        createdAt = LocalDateTime.now();
    }
}