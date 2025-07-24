package com.example.notificationproject.mapper;

import com.example.notificationproject.Model.dto.request.MessageTemplateRegisterRequestDTO;
import com.example.notificationproject.Model.dto.respond.MessageTemplateRespondDTO;
import com.example.notificationproject.Model.entity.MessageTemplate;
import com.example.notificationproject.util.TemplateParameterExtractor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.function.Function;


@Mapper()
public interface MessageTemplateMapper {



    @Mapping(target = "parameters", expression = "java(com.example.notificationproject.util.TemplateParameterExtractor.extract(messageTemplate))")
    MessageTemplateRespondDTO toDTO(MessageTemplate messageTemplate);
    MessageTemplate toEntity(MessageTemplateRegisterRequestDTO messageTemplateRegisterRequestDTO);
}
