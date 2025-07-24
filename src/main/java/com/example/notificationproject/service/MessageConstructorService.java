package com.example.notificationproject.service;

import com.example.notificationproject.Model.Aggregate.NotificationRequest;
import com.example.notificationproject.Model.entity.MessageTemplate;
import com.example.notificationproject.service.database.MessageTemplateService;
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
    private final MessageTemplateService messageTemplateService;
    private final ObjectMapper objectMapper;
    public Notification constructNotification(NotificationRequest notificationRequest) {
        List<String> content = getContent(notificationRequest);
        String title = content.get(0);
        String body = content.get(1);
        return Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
    }

    public SimpleMailMessage constructSimpleMailMessage(NotificationRequest notificationRequest) {
        List<String> content = getContent(notificationRequest);
        String title = content.get(0);
        String body = content.get(1);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setText(body);
        return message;
    }

    public ObjectNode constructTelegramMessage(NotificationRequest notificationRequest) {
        List<String> content = getContent(notificationRequest);
        ObjectNode jsonBody = objectMapper.createObjectNode();
        jsonBody.put("chat_id", "0");
        jsonBody.put("text", "<b>"+content.get(0)+"</b>\n"+content.get(1));
        jsonBody.put("parse_mode", "HTML");
        return jsonBody;
    }


    public List<String> getContent(NotificationRequest notificationRequest) {
        MessageTemplate messageTemplate = messageTemplateService.getMessageTemplateByName(notificationRequest.getTemplateName());
        StringSubstitutor substitutor = new StringSubstitutor(notificationRequest.getParameters());
        String title = substitutor.replace(messageTemplate.getTitle_template());
        String body = substitutor.replace(messageTemplate.getBody_template());
        List<String> content = new ArrayList<>();
        content.add(title);
        content.add(body);
        return content;
    }
}
