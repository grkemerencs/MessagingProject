package com.example.notificationproject.service.messaging;

import com.example.notificationproject.dto.request.NotificationRequestDTO;
import com.example.notificationproject.entity.Device;
import com.example.notificationproject.service.database.DeviceService;
import com.example.notificationproject.service.MessageConstructorService;
import com.google.firebase.messaging.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class FirebaseMessagingServiceService implements BaseMessagingService {
    public final DeviceService deviceService;
    private final MessageConstructorService messageConstructorService;

    public String sendNotifications(NotificationRequestDTO notificationRequestDTO) {
        Map<String,String> parameters = notificationRequestDTO.getParameters();
        Notification notification = messageConstructorService.constructNotification(notificationRequestDTO);
        Set<String> tokens = new HashSet<>();
        tokens.addAll(notificationRequestDTO.getDeviceTokens());
        // takes tokens for each device if from database.
        List<Device> devicesToAddToTokens = deviceService.getDeviceEntityById(notificationRequestDTO.getDeviceIds());
        tokens.addAll(devicesToAddToTokens.stream().map(Device::getFireBaseToken).toList());
        System.out.println("-----------tokens------------");
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

    public String sendNotificationsToAll(NotificationRequestDTO notificationRequestDTO) {
        List<String> tokens = deviceService.getAllDeviceEntities().stream().map(Device::getFireBaseToken).toList();
        notificationRequestDTO.setDeviceTokens(tokens);
        notificationRequestDTO.setDeviceIds(new ArrayList<>());
        System.out.println("asdasdasdsad");
        return sendNotifications(notificationRequestDTO);
    }

}
