package com.stellantis.operix.service;

import com.stellantis.operix.entity.Notification;
import com.stellantis.operix.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Notification> findNonLues(String username) {
        return List.of();
    }

    public void marquerLue(Integer id) {
        notificationRepository.findById(id).ifPresent(n -> {
            n.setLue(true);
            notificationRepository.save(n);
        });
    }

    public void genererAlertes() {
        // TODO: implémenter la génération métier des alertes.
    }
}