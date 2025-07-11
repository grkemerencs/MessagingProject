package com.example.notificationproject.controller;

import com.example.notificationproject.dto.request.RegisterTemplateRequestDTO;
import com.example.notificationproject.dto.respond.TemplateRespondDTO;
import com.example.notificationproject.service.database.TemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/templates")
public class TemplateController {

    @Autowired
    public final TemplateService templateService;


    @GetMapping
    public ResponseEntity<List<TemplateRespondDTO>> getAllTemplates() {
        List<TemplateRespondDTO> templateRespondDTOS = templateService.getAllTemplates();
        return ResponseEntity.ok(templateRespondDTOS);
    }

    @PostMapping
    public ResponseEntity<TemplateRespondDTO> createTemplate(@Valid @RequestBody RegisterTemplateRequestDTO request) {
        TemplateRespondDTO templateRespondDTO = templateService.registerTemplate(request);
        return ResponseEntity.ok(templateRespondDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateRespondDTO> getTemplateById(@PathVariable String id) {
        TemplateRespondDTO templateRespondDTO = templateService.getTemplateById(id);
        return ResponseEntity.ok(templateRespondDTO);
    }
}