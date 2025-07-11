package com.example.notificationproject.service.messaging;

import com.example.notificationproject.dto.request.NotificationRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class TelegramMessagingService implements BaseMessagingService {

    @Override
    public String sendNotifications(NotificationRequestDTO notificationRequestDTO) {
        return null;
    }

    @Override
    public String sendNotificationsToAll(NotificationRequestDTO notificationRequestDTO) {
        return null;
    }
}
