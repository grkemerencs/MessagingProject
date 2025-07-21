package com.example.notificationproject.controller;


import com.example.notificationproject.mapper.NotificationRequestMapper;
import com.example.notificationproject.service.messaging.MessagingStrategyService;
import com.example.notificationproject.Model.dto.request.NotificationRequestDTO;
import com.example.notificationproject.service.messaging.BaseMessagingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("notification")
public class NotificationController {
    private final MessagingStrategyService messagingStrategyService;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotificationToSingleDevice(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        BaseMessagingService service = messagingStrategyService.getServiceForRequest(notificationRequestDTO.getChannel());
        String response = service.sendNotifications(NotificationRequestMapper.toDomain.apply(notificationRequestDTO));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/send/batch")
    public ResponseEntity<String> sendNotificationToMultipleDevices(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        BaseMessagingService service = messagingStrategyService.getServiceForRequest(notificationRequestDTO.getChannel());
        String response = service.sendNotifications(NotificationRequestMapper.toDomain.apply(notificationRequestDTO));
        return ResponseEntity.ok(response);
    }
    @PostMapping("send/all")
    public ResponseEntity<String> sendNotificationToAllDevices(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        BaseMessagingService service = messagingStrategyService.getServiceForRequest(notificationRequestDTO.getChannel());
        String response = service.sendNotificationsToAll(NotificationRequestMapper.toDomain.apply(notificationRequestDTO));
        return ResponseEntity.ok(response);
    }
}
