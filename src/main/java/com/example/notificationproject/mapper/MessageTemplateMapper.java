package com.example.notificationproject.mapper;

import com.example.notificationproject.Model.dto.request.RegisterMessageTemplateRequestDTO;
import com.example.notificationproject.Model.dto.respond.MessageTemplateRespondDTO;
import com.example.notificationproject.Model.entity.MessageTemplate;
import com.example.notificationproject.util.TemplateParameterExtractor;

import java.util.function.Function;

public class MessageTemplateMapper {

    public static final Function<MessageTemplate, MessageTemplateRespondDTO> toDTO = (template -> {
        MessageTemplateRespondDTO dto = new MessageTemplateRespondDTO();
        dto.setId(template.getId());
        dto.setName(template.getName());
        dto.setTitle_template(template.getTitle_template());
        dto.setBody_template(template.getBody_template());

        // gives a list of parameters to confirm the Template
        dto.setParamaters(TemplateParameterExtractor.extract(template));
        return dto;
    });

    public static final Function<RegisterMessageTemplateRequestDTO, MessageTemplate> toEntity = (register -> {
        MessageTemplate entity = new MessageTemplate();
        entity.setName(register.getName());
        entity.setTitle_template(register.getTitle_template());
        entity.setBody_template(register.getBody_template());
        return entity;
    });
}
