package com.example.notificationproject.service;

import com.example.notificationproject.dto.request.NotificationRequestDTO;
import com.example.notificationproject.entity.Template;
import com.example.notificationproject.service.database.TemplateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class MessageConstructorServiceTest {

    @Mock
    private TemplateService templateService;

    private ObjectMapper objectMapper;

    @InjectMocks
    private MessageConstructorService messageConstructorService;

    @Test
    void testGetContent() {
        // Arrange
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO();
        notificationRequestDTO.setTemplateName("selamla");
        notificationRequestDTO.setParameters(new HashMap<>(Map.of(
                "name", "Görkem",
                "site", "Hacetteepe"
        )));
        Template template = new Template();
        template.setTitle_template("merhaba ${name},");
        template.setBody_template("${site}'e en yakın zamanda bekleniyorsunuz");

        Mockito.when(templateService.getTemplateEntityByName(anyString())).thenReturn(template);
        List<String> actualAnswers = new ArrayList<>(List.of("merhaba Görkem,",
                "Hacetteepe'e en yakın zamanda bekleniyorsunuz"));
        List<String> methodAnswers = messageConstructorService.getContent(notificationRequestDTO);
        assertEquals(actualAnswers.get(0),methodAnswers.get(0));
        assertEquals(actualAnswers.get(0),methodAnswers.get(0));
    }
} 