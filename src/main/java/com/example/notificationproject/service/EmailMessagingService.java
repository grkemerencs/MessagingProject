package com.example.notificationproject.service;

import com.example.notificationproject.dto.request.NotificationRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class EmailMessagingService implements BaseMessagingService{
    @Override
    public String sendNotifications(NotificationRequestDTO notificationRequestDTO) {
        return null;
    }

    @Override
    public String sendNotificationsToAllDevices(NotificationRequestDTO notificationRequestDTO) {
        return null;
    }
}
