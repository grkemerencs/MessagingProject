package com.example.notificationproject.service.messaging;

import com.example.notificationproject.Model.Aggregate.NotificationRequest;
import com.example.notificationproject.Model.entity.UserDevice;
import com.example.notificationproject.service.database.UserDeviceService;
import com.example.notificationproject.service.MessageConstructorService;
import com.google.firebase.messaging.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class FirebaseMessagingServiceService implements BaseMessagingService {
    public final UserDeviceService userDeviceService;
    private final MessageConstructorService messageConstructorService;

    public String sendNotifications(NotificationRequest notificationRequest) {
        Map<String,String> parameters = notificationRequest.getParameters();
        Notification notification = messageConstructorService.constructNotification(notificationRequest);
        Set<String> tokens = new HashSet<>();
        tokens.addAll(notificationRequest.getDeviceTokens());
        // takes tokens for each device if from database.
        List<UserDevice> devicesToAddToTokens = userDeviceService.getUserDeviceById(notificationRequest.getDeviceIds());
        tokens.addAll(devicesToAddToTokens.stream().map(UserDevice::getFireBaseToken).toList());
        System.out.println("-----------tokens------------");
        log.info("---token*---");
        tokens.stream().forEach(System.out::println);

        List<Message> messages = tokens.stream()
                .map(token -> Message.builder()
                        .setToken(token)
                        .setNotification(notification)
                        .build())
                .toList();

        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendEach(messages);
            String returnMessage = response.getSuccessCount()+ " Başarılı, "+
                    response.getFailureCount()+ " Başarısız Bildirim";
            System.out.println(returnMessage);
            return returnMessage;
        } catch (Exception e) {
            throw new RuntimeException("Bildirimler gönderilemedi: " + e.getMessage());
        }
    }

    public String sendNotificationsToAll(NotificationRequest notificationRequest) {
        List<String> tokens = userDeviceService.getAllUserDevices().stream().map(UserDevice::getFireBaseToken).toList();
        notificationRequest.setDeviceTokens(tokens);
        notificationRequest.setDeviceIds(new ArrayList<>());
        System.out.println("asdasdasdsad");
        return sendNotifications(notificationRequest);
    }

}
