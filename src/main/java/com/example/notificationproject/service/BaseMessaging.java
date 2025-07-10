package com.example.notificationproject.service;

import com.example.notificationproject.dto.request.NotificationRequestDTO;

public interface BaseMessaging {
    public String sendNotifications(NotificationRequestDTO notificationRequestDTO);
    public String sendNotificationsToAllDevices(NotificationRequestDTO notificationRequestDTO);

}