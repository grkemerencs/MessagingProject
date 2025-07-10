package com.example.notificationproject.config;

import com.example.notificationproject.Enum.Channel;
import com.example.notificationproject.service.BaseMessagingService;
import com.example.notificationproject.service.EmailMessagingService;
import com.example.notificationproject.service.FirebaseMessagingServiceService;
import com.example.notificationproject.service.TelegramMessagingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingServiceConfig {
    @Bean
    public Map<Channel, BaseMessagingService> messagingServiceMapper(
            EmailMessagingService emailService,
            TelegramMessagingService telegramService,
            FirebaseMessagingServiceService notificationService
    ) {
        Map<Channel, BaseMessagingService> map = new HashMap<>();
        map.put(Channel.EMAIL, emailService);
        map.put(Channel.TELEGRAM, telegramService);
        map.put(Channel.NOTIFICATION, notificationService);
        return map;
    }
}
