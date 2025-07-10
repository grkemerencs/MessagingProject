package com.example.notificationproject.controller;


import com.example.notificationproject.Enum.Channel;
import com.example.notificationproject.Enum.DevicePlatform;
import com.example.notificationproject.dto.request.NotificationRequestDTO;
import com.example.notificationproject.service.BaseMessagingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("notifications")
public class NotificationController {
    // all arg mı req arg cons mu?
    @Autowired
    private final Map<Channel,BaseMessagingService> messagingServiceMapper;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotificationToSingleDevice(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        return sendNotificationToMultipleDevices(notificationRequestDTO);
    }

    @PostMapping("/send/batch")
    public ResponseEntity<String> sendNotificationToMultipleDevices(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        String response = messagingServiceMapper
                .get(notificationRequestDTO.getChannel())
                .sendNotifications(notificationRequestDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("send/all")
    public ResponseEntity<String> sendNotificationToAllDevices(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        String response = messagingServiceMapper
                .get(notificationRequestDTO.getChannel())
                .sendNotificationsToAllDevices(notificationRequestDTO);
        return ResponseEntity.ok(response);
    }
}
