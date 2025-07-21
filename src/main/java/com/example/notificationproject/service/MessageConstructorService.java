package com.example.notificationproject.service;

import com.example.notificationproject.dto.request.NotificationRequestDTO;
import com.example.notificationproject.entity.Template;
import com.example.notificationproject.service.database.TemplateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageConstructorService {
    private final TemplateService templateService;
    private final ObjectMapper objectMapper;
    public Notification constructNotification(NotificationRequestDTO notificationRequestDTO) {
        List<String> content = getContent(notificationRequestDTO);
        String title = content.get(0);
        String body = content.get(1);
        return Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
    }

    public SimpleMailMessage constructSimpleMailMessage(NotificationRequestDTO notificationRequestDTO) {
        List<String> content = getContent(notificationRequestDTO);
        String title = content.get(0);
        String body = content.get(1);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setText(body);
        return message;
    }

    public ObjectNode constructTelegramMessage(NotificationRequestDTO notificationRequestDTO) {
        List<String> content = getContent(notificationRequestDTO);
        ObjectNode jsonBody = objectMapper.createObjectNode();
        jsonBody.put("chat_id", "0");
        jsonBody.put("text", "<b>"+content.get(0)+"</b>\n"+content.get(1));
        jsonBody.put("parse_mode", "HTML");
        return jsonBody;
    }


    public List<String> getContent(NotificationRequestDTO notificationRequestDTO) {
        Template template = templateService.getTemplateEntityByName(notificationRequestDTO.getTemplateName());
        StringSubstitutor substitutor = new StringSubstitutor(notificationRequestDTO.getParameters());
        String title = substitutor.replace(template.getTitle_template());
        String body = substitutor.replace(template.getBody_template());
        List<String> content = new ArrayList<>();
        content.add(title);
        content.add(body);
        return content;
    }
}
