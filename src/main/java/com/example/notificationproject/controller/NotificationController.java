package com.example.notificationproject.controller;


import com.example.notificationproject.mapper.NotificationRequestMapper;
import com.example.notificationproject.service.messaging.MessagingStrategyService;
import com.example.notificationproject.Model.dto.request.NotificationRequestDTO;
import com.example.notificationproject.service.messaging.BaseMessagingService;
import com.example.notificationproject.Model.dto.respond.ApiRespondDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
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
    private final NotificationRequestMapper notificationRequestMapper = Mappers.getMapper(NotificationRequestMapper.class);

    @PostMapping("/send")
    public ResponseEntity<ApiRespondDTO<String>> sendNotificationToSingleDevice(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        BaseMessagingService service = messagingStrategyService.getServiceForRequest(notificationRequestDTO.getChannel());
        String response = service.sendNotifications(notificationRequestMapper.toDomain(notificationRequestDTO));
        return ResponseEntity.ok(new ApiRespondDTO<>(response));
    }

    @PostMapping("/send/batch")
    public ResponseEntity<ApiRespondDTO<String>> sendNotificationToMultipleDevices(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        BaseMessagingService service = messagingStrategyService.getServiceForRequest(notificationRequestDTO.getChannel());
        String response = service.sendNotifications(notificationRequestMapper.toDomain(notificationRequestDTO));
        return ResponseEntity.ok(new ApiRespondDTO<>(response));
    }
    @PostMapping("send/all")
    public ResponseEntity<ApiRespondDTO<String>> sendNotificationToAllDevices(
            @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        BaseMessagingService service = messagingStrategyService.getServiceForRequest(notificationRequestDTO.getChannel());
        String response = service.sendNotificationsToAll(notificationRequestMapper.toDomain(notificationRequestDTO));
        return ResponseEntity.ok(new ApiRespondDTO<>(response));
    }
}
