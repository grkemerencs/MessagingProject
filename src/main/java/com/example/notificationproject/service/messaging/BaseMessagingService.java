package com.example.notificationproject.service.messaging;

import com.example.notificationproject.Model.Aggregate.NotificationRequest;

import java.util.ArrayList;

public interface BaseMessagingService {
    public String sendNotifications(NotificationRequest notificationRequest);
    public String sendNotificationsToAll(NotificationRequest notificationRequest);

}
