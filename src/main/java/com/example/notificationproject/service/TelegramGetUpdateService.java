package com.example.notificationproject.service;

import com.example.notificationproject.dto.TelegramUpdateDTO;
import com.example.notificationproject.service.database.TelegramIdStateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramGetUpdateService {

    @Value("${telegram.bot.api-url}")
    private String telegramApiUrl;

    private final TelegramIdStateService telegramIdStateService;

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<TelegramUpdateDTO> getLastTelegramUpdateDTO() {
        try {
            String url = telegramApiUrl +"/getUpdates" +"?offset=" + (telegramIdStateService.getState() + 1);
            String response = restTemplate.getForObject(url, String.class);

            JsonNode root = objectMapper.readTree(response);
            JsonNode result = root.get("result");

            if (result != null && result.isArray() && result.size() > 0) {
                List<TelegramUpdateDTO> telegramUpdateDTOList = new ArrayList<>();
                long maxUpdateId = 0;

                for (JsonNode node : result) {
                    JsonNode messageNode = node.get("message");
                    System.out.println(messageNode);
                    if (messageNode == null || messageNode.get("chat") == null) continue;

                    JsonNode chat = messageNode.get("chat");

                    TelegramUpdateDTO telegramUpdateDTO = new TelegramUpdateDTO();
                    telegramUpdateDTO.setChatId(chat.get("id").asLong());
                    telegramUpdateDTO.setFromName(chat.get("first_name").asText());

                    telegramUpdateDTOList.add(telegramUpdateDTO);

                    long currentUpdateId = node.get("update_id").asLong();
                    if (currentUpdateId > maxUpdateId) {
                        maxUpdateId = currentUpdateId;
                    }
                }

                telegramIdStateService.setState(maxUpdateId);
                System.out.println("new telegram state is " + telegramIdStateService.getState());
                return telegramUpdateDTOList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}