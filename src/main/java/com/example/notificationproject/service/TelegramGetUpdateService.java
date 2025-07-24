package com.example.notificationproject.service;

import com.example.notificationproject.Model.dto.UserTelegramAccountUpdateDTO;
import com.example.notificationproject.service.database.TelegramIdStateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramGetUpdateService {

    @Value("${telegram.bot.api-url}")
    private String telegramApiUrl;

    private final TelegramIdStateService telegramIdStateService;

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<UserTelegramAccountUpdateDTO> getLastTelegramUpdateDTO() {
        try {
            String url = telegramApiUrl +"/getUpdates" +"?offset=" + (telegramIdStateService.getState() + 1);
            String response = restTemplate.getForObject(url, String.class);

            JsonNode root = objectMapper.readTree(response);
            JsonNode result = root.get("result");

            if (result != null && result.isArray() && !result.isEmpty()) {
                List<UserTelegramAccountUpdateDTO> userTelegramAccountUpdateDTOList = new ArrayList<>();
                long maxUpdateId = 0;

                for (JsonNode node : result) {
                    JsonNode messageNode = node.get("message");
                    if (messageNode == null || messageNode.get("chat") == null) continue;

                    JsonNode chat = messageNode.get("chat");

                    UserTelegramAccountUpdateDTO userTelegramAccountUpdateDTO = new UserTelegramAccountUpdateDTO();
                    userTelegramAccountUpdateDTO.setChatId(chat.get("id").asLong());
                    userTelegramAccountUpdateDTO.setFromName(chat.get("first_name").asText());

                    userTelegramAccountUpdateDTOList.add(userTelegramAccountUpdateDTO);

                    long currentUpdateId = node.get("update_id").asLong();
                    if (currentUpdateId > maxUpdateId) {
                        maxUpdateId = currentUpdateId;
                    }
                }

                telegramIdStateService.setState(maxUpdateId);
                log.info("new telegram state is {}", telegramIdStateService.getState());
                return userTelegramAccountUpdateDTOList;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}