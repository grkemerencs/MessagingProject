package com.example.notificationproject.service.messaging;

import com.example.notificationproject.dto.request.NotificationRequestDTO;
import com.example.notificationproject.entity.Telegram;
import com.example.notificationproject.repository.TelegramRepository;
import com.example.notificationproject.service.MessageConstructorService;
import com.example.notificationproject.service.database.TelegramService;
import com.fasterxml.jackson.databind.JsonNode;
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
    private final TelegramService telegramService;
    RestTemplate restTemplate = new RestTemplate();
    private final MessageConstructorService messageConstructorService;

    @PostConstruct
    public void init() {
        telegramApiUrl += "/sendMessage";
        System.out.println("TelegramMessagingService başlatıldı. API URL: " + telegramApiUrl);
    }

    @Override
    public String sendNotifications(NotificationRequestDTO notificationRequestDTO) {

        ObjectNode jsonBody = messageConstructorService.constructTelegramMessage(notificationRequestDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        int failedMessageCount = 0;
        for (long chatId : notificationRequestDTO.getTelegramChatIds()) {
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
        return "Başarılı mesaj sayısı: "+(notificationRequestDTO.getTelegramChatIds().size()-failedMessageCount)+
                "\nBaşarısız mesaj sayısı: "+failedMessageCount;
    }

    @Override
    public String sendNotificationsToAll(NotificationRequestDTO notificationRequestDTO) {
        List<Telegram> telegrams = telegramService.getAllTelegrams();
        List<Long> telegramChatIds = telegrams.stream().map(Telegram::getTelegramId).toList();
        notificationRequestDTO.setTelegramChatIds(telegramChatIds);
        return sendNotifications(notificationRequestDTO);
    }
}
