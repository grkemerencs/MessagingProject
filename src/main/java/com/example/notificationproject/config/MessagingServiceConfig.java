package com.example.notificationproject.config;

import com.example.notificationproject.Enum.Channel;
import com.example.notificationproject.service.messaging.BaseMessagingService;
import com.example.notificationproject.service.messaging.EmailMessagingService;
import com.example.notificationproject.service.messaging.FirebaseMessagingServiceService;
import com.example.notificationproject.service.messaging.TelegramMessagingService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class MessagingServiceConfig {
    // bu klassı messaging service selector ve messaging service config olarak ikiye mi ayırlamıyım yoksa sadece selector olarak mı güncellemeyliyim?
    private final Map<Channel, BaseMessagingService> messagingServiceMap = new HashMap<>();

    public MessagingServiceConfig(
            EmailMessagingService emailService,
            TelegramMessagingService telegramService,
            FirebaseMessagingServiceService notificationService
    ) {
        messagingServiceMap.put(Channel.EMAIL, emailService);
        messagingServiceMap.put(Channel.TELEGRAM, telegramService);
        messagingServiceMap.put(Channel.NOTIFICATION, notificationService);
    }

    public BaseMessagingService getServiceForRequest(Channel channel) {
        return messagingServiceMap.get(channel);
    }

}
