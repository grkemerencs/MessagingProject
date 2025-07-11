package com.example.notificationproject.controller;


import com.example.notificationproject.Enum.Channel;
import com.example.notificationproject.config.MessagingServiceConfig;
import com.example.notificationproject.dto.request.NotificationRequestDTO;
import com.example.notificationproject.service.messaging.BaseMessagingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("notification")
public class NotificationController {
    private final MessagingServiceConfig messagingServiceConfig;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotificationToSingleDevice(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        BaseMessagingService service = messagingServiceConfig.getServiceForRequest(notificationRequestDTO.getChannel());
        return sendNotificationToMultipleDevices(notificationRequestDTO);
    }

    @PostMapping("/send/batch")
    public ResponseEntity<String> sendNotificationToMultipleDevices(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        BaseMessagingService service = messagingServiceConfig.getServiceForRequest(notificationRequestDTO.getChannel());
        String response = service.sendNotifications(notificationRequestDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("send/all")
    public ResponseEntity<String> sendNotificationToAllDevices(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        BaseMessagingService service = messagingServiceConfig.getServiceForRequest(notificationRequestDTO.getChannel());
        String response = service.sendNotificationsToAll(notificationRequestDTO);
        return ResponseEntity.ok(response);
    }
}
