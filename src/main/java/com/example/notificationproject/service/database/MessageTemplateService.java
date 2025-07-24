package com.example.notificationproject.service.database;

import com.example.notificationproject.Model.dto.request.MessageTemplateRegisterRequestDTO;
import com.example.notificationproject.Model.entity.MessageTemplate;
import com.example.notificationproject.exception.DataBaseException;
import com.example.notificationproject.exception.NotFoundException;
import com.example.notificationproject.mapper.MessageTemplateMapper;
import com.example.notificationproject.repository.MessageTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageTemplateService {
    @Autowired
    private final MessageTemplateRepository messageTemplateRepository;
    private final MessageTemplateMapper messageTemplateMapper =  Mappers.getMapper(MessageTemplateMapper.class);


    public final List<MessageTemplate> getAllMessageTemplates() {
        return messageTemplateRepository.findAll();
    }

    public final MessageTemplate deleteMessageTemplateByName(String templateName) {
        MessageTemplate messageTemplate = getMessageTemplateByName(templateName);
        messageTemplateRepository.delete(messageTemplate);
        return messageTemplate;
    }

    public final MessageTemplate getMessageTemplateByName(String templateName) {
        return messageTemplateRepository.findTemplateByName(templateName).orElseThrow(()->new NotFoundException.MessageTemplateNotFound(templateName));
    }
    public final MessageTemplate registerMessageTemplate(MessageTemplateRegisterRequestDTO messageTemplateRegisterRequestDTO) {
        try {
            return messageTemplateRepository.save(messageTemplateMapper.toEntity(messageTemplateRegisterRequestDTO));
        } catch (Exception ex) {
            throw new DataBaseException("Error in saving MessageTemplate \""+ messageTemplateRegisterRequestDTO.getName()+"\"");
        }

    }


}
