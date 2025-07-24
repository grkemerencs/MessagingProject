package com.example.notificationproject.service.messaging;

import com.example.notificationproject.Enum.Channel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class MessagingStrategyService {
    private final Map<Channel, BaseMessagingService> messagingServiceMap = new HashMap<>();

    public MessagingStrategyService(
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
