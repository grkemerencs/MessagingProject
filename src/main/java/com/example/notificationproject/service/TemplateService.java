package com.example.notificationproject.service;

import com.example.notificationproject.dto.request.RegisterTemplateRequestDTO;
import com.example.notificationproject.dto.respond.TemplateRequestDTO;
import com.example.notificationproject.entity.Template;
import com.example.notificationproject.mapper.TemplateMapper;
import com.example.notificationproject.repository.TemplateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {
    @Autowired
    private final TemplateRepository repo;


    public final List<TemplateRequestDTO> getAllTemplates() {
        List<Template> templates = repo.findAll();
        return templates.stream().map(TemplateMapper.toDTO).toList();
    }

    public final Template getTemplateEntityById(int id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Template not found with id: " + "id"));
    }

    public final TemplateRequestDTO getTemplateById(int id) {
        return TemplateMapper.toDTO.apply(getTemplateEntityById(id));
    }

    public final TemplateRequestDTO registerTemplate(RegisterTemplateRequestDTO registerTemplateRequestDTO) {
        Template template = repo.save(TemplateMapper.toEntity.apply(registerTemplateRequestDTO));
        return TemplateMapper.toDTO.apply(template);
    }

}
