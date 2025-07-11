package com.example.notificationproject.service.messaging;

import com.example.notificationproject.dto.request.NotificationRequestDTO;

public interface BaseMessagingService {
    public String sendNotifications(NotificationRequestDTO notificationRequestDTO);
    public String sendNotificationsToAll(NotificationRequestDTO notificationRequestDTO);

}
