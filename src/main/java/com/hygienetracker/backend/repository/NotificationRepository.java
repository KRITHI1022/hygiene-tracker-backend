package com.hygienetracker.backend.repository;

import com.hygienetracker.backend.model.Notification;
import com.hygienetracker.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
    List<Notification> findByUserAndIsRead(User user, Boolean isRead);
}