package com.example.notificationproject.controller;

import com.example.notificationproject.Model.dto.request.MessageTemplateRegisterRequestDTO;
import com.example.notificationproject.Model.dto.respond.MessageTemplateRespondDTO;
import com.example.notificationproject.Model.dto.respond.ApiRespondDTO;
import com.example.notificationproject.mapper.MessageTemplateMapper;
import com.example.notificationproject.service.database.MessageTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.notificationproject.Model.entity.MessageTemplate;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/templates")
public class MessageTemplateController {

    @Autowired
    private final MessageTemplateService messageTemplateService;
    private final MessageTemplateMapper messageTemplateMapper = Mappers.getMapper(MessageTemplateMapper.class);


    @GetMapping
    public ResponseEntity<ApiRespondDTO<List<MessageTemplateRespondDTO>>> getAllMessageTemplates() {
        List<MessageTemplate> messageTemplates = messageTemplateService.getAllMessageTemplates();
        return ResponseEntity.ok(new ApiRespondDTO<>(messageTemplates.stream().map(messageTemplateMapper::toDTO).toList()));
    }

    @PostMapping
    public ResponseEntity<ApiRespondDTO<MessageTemplateRespondDTO>> createMessageTemplate(@Valid @RequestBody MessageTemplateRegisterRequestDTO request) {
        MessageTemplate messageTemplate = messageTemplateService.registerMessageTemplate(request);
        return ResponseEntity.ok(new ApiRespondDTO<>(messageTemplateMapper.toDTO(messageTemplate)));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ApiRespondDTO<MessageTemplateRespondDTO>> getMessageTemplateByName(@PathVariable String name) {
        MessageTemplate messageTemplate = messageTemplateService.getMessageTemplateByName(name);
        return ResponseEntity.ok(new ApiRespondDTO<>(messageTemplateMapper.toDTO(messageTemplate)));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<ApiRespondDTO<MessageTemplateRespondDTO>> deleteMessageTemplate(@PathVariable String name) {
        MessageTemplate messageTemplate = messageTemplateService.deleteMessageTemplateByName(name);
        return ResponseEntity.ok(new ApiRespondDTO<>(messageTemplateMapper.toDTO(messageTemplate)));
    }
}