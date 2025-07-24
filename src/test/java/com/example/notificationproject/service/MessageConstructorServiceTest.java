package com.example.notificationproject.service;

import com.example.notificationproject.Model.dto.request.NotificationRequestDTO;
import com.example.notificationproject.Model.entity.MessageTemplate;
import com.example.notificationproject.mapper.NotificationRequestMapper;
import com.example.notificationproject.service.database.MessageTemplateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class MessageConstructorServiceTest {

    @Mock
    private MessageTemplateService messageTemplateService;
    private final NotificationRequestMapper notificationRequestMapper = Mappers.getMapper(NotificationRequestMapper.class);

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
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setTitle_template("merhaba ${name},");
        messageTemplate.setBody_template("${site}'e en yakın zamanda bekleniyorsunuz");

        Mockito.when(messageTemplateService.getMessageTemplateByName(anyString())).thenReturn(messageTemplate);
        List<String> actualAnswers = new ArrayList<>(List.of("merhaba Görkem,",
                "Hacetteepe'e en yakın zamanda bekleniyorsunuz"));
        List<String> methodAnswers = messageConstructorService.getContent(notificationRequestMapper.toDomain(notificationRequestDTO));
        assertEquals(actualAnswers.get(0),methodAnswers.get(0));
        assertEquals(actualAnswers.get(0),methodAnswers.get(0));
    }
} 