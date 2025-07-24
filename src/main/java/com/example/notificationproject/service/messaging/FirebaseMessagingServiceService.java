package com.example.notificationproject.service.messaging;

import com.example.notificationproject.Model.Aggregate.NotificationRequest;
import com.example.notificationproject.Model.entity.UserDevice;
import com.example.notificationproject.exception.MessageException;
import com.example.notificationproject.service.database.UserDeviceService;
import com.example.notificationproject.service.MessageConstructorService;
import com.google.firebase.FirebaseException;
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
        Set<String> tokens = new TreeSet<>();
        tokens.addAll(notificationRequest.getDeviceTokens());
        List<UserDevice> devicesToAddToTokens = userDeviceService.getUserDeviceById(notificationRequest.getDeviceIds());
        tokens.addAll(devicesToAddToTokens.stream().map(UserDevice::getFireBaseToken).toList());
        List<String> tokenList = new ArrayList<>(tokens);
        List<Message> messages = tokenList.stream()
                .map(token -> Message.builder()
                        .setToken(token)
                        .setNotification(notification)
                        .build())
                .toList();
        List<String> successfulIds = new ArrayList<>();
        try {
            BatchResponse responses = FirebaseMessaging.getInstance().sendEach(messages);
            for(int i = 0 ; i < responses.getResponses().size(); i++) {
                String message = responses.getResponses().get(i).getMessageId();
                if(message != null) {
                    successfulIds.add(tokenList.get(i));
                }
            }
            return "Message successfully delivered To: "+ Arrays.toString(successfulIds.toArray());
        } catch (Exception e) {
            throw new MessageException.FirebaseMessagingException("Notifications couldn't send: " + e.getMessage()) {
            };
        }
    }

    public String sendNotificationsToAll(NotificationRequest notificationRequest) {
        List<String> tokens = userDeviceService.getAllUserDevices().stream().map(UserDevice::getFireBaseToken).toList();
        notificationRequest.setDeviceTokens(tokens);
        notificationRequest.setDeviceIds(new ArrayList<>());
        return sendNotifications(notificationRequest);
    }

}
