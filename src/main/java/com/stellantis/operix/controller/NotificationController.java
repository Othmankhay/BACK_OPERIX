package com.stellantis.operix.controller;

import com.stellantis.operix.entity.Notification;
import com.stellantis.operix.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notifService;

    @GetMapping
    public ResponseEntity<List<Notification>> findAll(
            @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(
                notifService.findNonLues(user.getUsername()));
    }
    @PutMapping("/{id}/lue")
    public ResponseEntity<Void> marquerLue(
            @PathVariable Integer id) {
        notifService.marquerLue(id);
        return ResponseEntity.noContent().build();
    }
}