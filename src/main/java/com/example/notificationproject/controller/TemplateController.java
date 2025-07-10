package com.example.notificationproject.controller;

import com.example.notificationproject.dto.request.RegisterTemplateRequestDTO;
import com.example.notificationproject.dto.respond.TemplateRequestDTO;
import com.example.notificationproject.service.TemplateService;
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
    public ResponseEntity<List<TemplateRequestDTO>> getAllTemplates() {
        List<TemplateRequestDTO> templateRequestDTOS = templateService.getAllTemplates();
        return ResponseEntity.ok(templateRequestDTOS);
    }



    @PostMapping
    public ResponseEntity<TemplateRequestDTO> createTemplate(@Valid @RequestBody RegisterTemplateRequestDTO request) {
        TemplateRequestDTO templateRequestDTO = templateService.registerTemplate(request);
        return ResponseEntity.ok(templateRequestDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TemplateRequestDTO> getTemplateById(@PathVariable int id) {
        TemplateRequestDTO templateRequestDTO = templateService.getTemplateById(id);
        return ResponseEntity.ok(templateRequestDTO);
    }
}