package com.example.notificationproject.service;

import com.example.notificationproject.dto.request.NotificationRequestDTO;

public interface BaseMessagingService {
    public String sendNotifications(NotificationRequestDTO notificationRequestDTO);
    public String sendNotificationsToAllDevices(NotificationRequestDTO notificationRequestDTO);

}
