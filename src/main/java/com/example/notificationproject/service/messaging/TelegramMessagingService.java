package com.example.notificationproject.service.messaging;

import com.example.notificationproject.Model.Aggregate.NotificationRequest;
import com.example.notificationproject.Model.entity.UserTelegramAccount;
import com.example.notificationproject.service.MessageConstructorService;
import com.example.notificationproject.service.database.UserTelegramAccountService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramMessagingService implements BaseMessagingService {

    @Value("${telegram.bot.api-url}")
    private String telegramApiUrl;
    private final ObjectMapper objectMapper;
    private final UserTelegramAccountService userTelegramAccountService;
    RestTemplate restTemplate = new RestTemplate();
    private final MessageConstructorService messageConstructorService;

    @PostConstruct
    public void init() {
        telegramApiUrl += "/sendMessage";
        System.out.println("TelegramMessagingService başlatıldı. API URL: " + telegramApiUrl);
    }

    @Override
    public String sendNotifications(NotificationRequest notificationRequest) {

        ObjectNode jsonBody = messageConstructorService.constructTelegramMessage(notificationRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        int failedMessageCount = 0;
        for (long chatId : notificationRequest.getTelegramChatIds()) {
            jsonBody.put("chat_id", chatId);
            HttpEntity<ObjectNode> request = new HttpEntity<>(jsonBody, headers);
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(telegramApiUrl, request, String.class);
                if (!response.getStatusCode().is2xxSuccessful()) {
                    System.err.println("Telegram mesaj gönderim hatası: " + response.getBody());
                    failedMessageCount++;
                }
            } catch (Exception e) {
                System.err.println("Telegram mesaj gönderilirken hata: " + e.getMessage());
            }
        }
        return "Başarılı mesaj sayısı: "+(notificationRequest.getTelegramChatIds().size()-failedMessageCount)+
                "\nBaşarısız mesaj sayısı: "+failedMessageCount;
    }

    @Override
    public String sendNotificationsToAll(NotificationRequest notificationRequest) {
        List<UserTelegramAccount> userTelegramAccounts = userTelegramAccountService.getAllUserTelegramAccounts();
        List<Long> telegramChatIds = userTelegramAccounts.stream().map(UserTelegramAccount::getTelegramId).toList();
        notificationRequest.setTelegramChatIds(telegramChatIds);
        return sendNotifications(notificationRequest);
    }
}
