package com.example.notificationproject.service.database;

import com.example.notificationproject.dto.request.RegisterTemplateRequestDTO;
import com.example.notificationproject.dto.respond.TemplateRespondDTO;
import com.example.notificationproject.entity.Template;
import com.example.notificationproject.exception.NotFoundException;
import com.example.notificationproject.mapper.TemplateMapper;
import com.example.notificationproject.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {
    @Autowired
    private final TemplateRepository repo;


    public final List<TemplateRespondDTO> getAllTemplates() {
        List<Template> templates = repo.findAll();
        return templates.stream().map(TemplateMapper.toDTO).toList();
    }

    public final Template getTemplateEntityById(String id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Template not found with id: " + "id"));
    }

    public final TemplateRespondDTO getTemplateById(String id) {
        return TemplateMapper.toDTO.apply(getTemplateEntityById(id));
    }

    public final TemplateRespondDTO registerTemplate(RegisterTemplateRequestDTO registerTemplateRequestDTO) {
        Template template = repo.save(TemplateMapper.toEntity.apply(registerTemplateRequestDTO));
        return TemplateMapper.toDTO.apply(template);
    }

}
