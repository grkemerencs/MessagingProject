package com.example.notificationproject.mapper;

import com.example.notificationproject.dto.request.RegisterTemplateRequestDTO;
import com.example.notificationproject.dto.respond.TemplateRequestDTO;
import com.example.notificationproject.entity.Template;
import com.example.notificationproject.util.TemplateParameterExtractor;

import java.util.function.Function;

public class TemplateMapper {

    public static final Function<Template, TemplateRequestDTO> toDTO = (template -> {
        TemplateRequestDTO dto = new TemplateRequestDTO();
        dto.setId(template.getId());
        dto.setName(template.getName());
        dto.setTitle_template(template.getTitle_template());
        dto.setBody_template(template.getBody_template());

        // gives a list of parameters to confirm the Template
        dto.setParamaters(TemplateParameterExtractor.extract(template));
        return dto;
    });

    public static final Function<RegisterTemplateRequestDTO, Template> toEntity = (register -> {
        Template entity = new Template();
        entity.setId(register.getId());
        entity.setName(register.getName());
        entity.setTitle_template(register.getTitle_template());
        entity.setBody_template(register.getBody_template());
        return entity;
    });

}
