package com.example.notificationproject.service.messaging;

import com.example.notificationproject.Model.Aggregate.NotificationRequest;

public interface BaseMessagingService {
    public String sendNotifications(NotificationRequest notificationRequest);
    public String sendNotificationsToAll(NotificationRequest notificationRequest);

}
