package com.example.notificationproject.controller;

import com.example.notificationproject.Model.dto.request.RegisterMessageTemplateRequestDTO;
import com.example.notificationproject.service.database.MessageTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public final MessageTemplateService messageTemplateService;


    @GetMapping
    public ResponseEntity<List<MessageTemplate>> getAllMessageTemplates() {
        List<MessageTemplate> messageTemplates = messageTemplateService.getAllMessageTemplates();
        return ResponseEntity.ok(messageTemplates);
    }

    @PostMapping
    public ResponseEntity<MessageTemplate> createMessageTemplate(@Valid @RequestBody RegisterMessageTemplateRequestDTO request) {
        MessageTemplate messageTemplate = messageTemplateService.registerMessageTemplate(request);
        return ResponseEntity.ok(messageTemplate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageTemplate> getMessageTemplateById(@PathVariable String id) {
        MessageTemplate messageTemplate = messageTemplateService.getMessageTemplateById(id);
        return ResponseEntity.ok(messageTemplate);
    }
}