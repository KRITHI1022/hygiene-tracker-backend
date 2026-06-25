package com.hygienetracker.backend.service;

import com.hygienetracker.backend.model.Bin;
import com.hygienetracker.backend.model.Notification;
import com.hygienetracker.backend.model.User;
import com.hygienetracker.backend.repository.BinRepository;
import com.hygienetracker.backend.repository.NotificationRepository;
import com.hygienetracker.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BinService {

    private final BinRepository binRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public List<Bin> getBinsByWard(String wardNumber) {
        return binRepository.findByWardNumber(wardNumber);
    }

    public Bin addBin(Bin bin) {
        return binRepository.save(bin);
    }

    // Runs every 60 seconds
    @Scheduled(fixedRate = 60000)
    public void simulateBinLevels() {
        List<Bin> bins = binRepository.findAll();
        Random random = new Random();

        for (Bin bin : bins) {
            int oldLevel = bin.getFillLevel();
            int increase = random.nextInt(6) + 3; // 3-8% increase
            int newLevel = Math.min(bin.getFillLevel() + increase, 100);
            bin.setFillLevel(newLevel);
            bin.setLastUpdated(LocalDateTime.now());
            binRepository.save(bin);

            // Alert when bin crosses 80%
            if (newLevel >= 80 && oldLevel  < 80) {
                notifyOfficialForWard(bin);
            }
        }
    }

    private void notifyOfficialForWard(Bin bin) {
        List<User> officials = userRepository.findAll().stream()
                .filter(u -> u.getRole() == User.Role.OFFICIAL
                        && bin.getWardNumber().equals(u.getWardNumber()))
                .toList();

        for (User official : officials) {
            Notification notification = new Notification();
            notification.setUser(official);
            notification.setMessage("Bin " + bin.getBinName() +
                    " at " + bin.getLocationName() +
                    " is " + bin.getFillLevel() + "% full!");
            notification.setType("BIN_ALERT");
            notificationRepository.save(notification);
        }
    }
}