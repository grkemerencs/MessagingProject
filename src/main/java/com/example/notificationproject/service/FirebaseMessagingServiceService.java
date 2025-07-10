package com.example.notificationproject.service;

import com.example.notificationproject.dto.request.NotificationRequestDTO;
import com.example.notificationproject.entity.Device;
import com.example.notificationproject.entity.Template;
import com.example.notificationproject.util.NotificationConstructor;
import com.google.firebase.messaging.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class FirebaseMessagingServiceService implements BaseMessagingService {
    public final DeviceService deviceService;
    public final TemplateService templateService;

    public String sendNotifications(NotificationRequestDTO notificationRequestDTO) {
        Template template = templateService.getTemplateEntityById(notificationRequestDTO.getTemplateId());
        Map<String,String> parameters = notificationRequestDTO.getParameters();
        Notification notification = NotificationConstructor.construct(template,parameters);
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

    public String sendNotificationsToAllDevices(NotificationRequestDTO notificationRequestDTO) {
        List<String> tokens = deviceService.getAllDeviceEntities().stream().map(Device::getFireBaseToken).toList();
        notificationRequestDTO.setDeviceTokens(tokens);
        notificationRequestDTO.setDeviceIds(new ArrayList<>());
        System.out.println("asdasdasdsad");
        return sendNotifications(notificationRequestDTO);
    }

}
