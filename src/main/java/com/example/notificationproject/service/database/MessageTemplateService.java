package com.example.notificationproject.service.database;

import com.example.notificationproject.Model.dto.request.RegisterMessageTemplateRequestDTO;
import com.example.notificationproject.Model.dto.respond.MessageTemplateRespondDTO;
import com.example.notificationproject.Model.entity.MessageTemplate;
import com.example.notificationproject.exception.NotFoundException;
import com.example.notificationproject.mapper.MessageTemplateMapper;
import com.example.notificationproject.repository.MessageTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageTemplateService {
    @Autowired
    private final MessageTemplateRepository repo;


    public final List<MessageTemplate> getAllMessageTemplates() {
        return repo.findAll();
    }

    public final MessageTemplate getMessageTemplateById(String id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("MessageTemplate not found with id: " + id));
    }

    public final MessageTemplateRespondDTO getTemplateById(String id) {
        return MessageTemplateMapper.toDTO.apply(getMessageTemplateById(id));
    }

    public final MessageTemplate getTemplateEntityByName(String name) {
        return repo.findTemplateByName(name).orElseThrow(()->new NotFoundException("Template not found with name: "+
                name));
    }

    public final MessageTemplate registerMessageTemplate(RegisterMessageTemplateRequestDTO registerMessageTemplateRequestDTO) {
        MessageTemplate messageTemplate = repo.save(MessageTemplateMapper.toEntity.apply(registerMessageTemplateRequestDTO));
        return messageTemplate;
    }

}
