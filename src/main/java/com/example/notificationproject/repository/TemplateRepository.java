package com.example.notificationproject.repository;

import com.example.notificationproject.entity.Telegram;
import com.example.notificationproject.entity.Template;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TemplateRepository extends MongoRepository<Template, String> {
    Optional<Template> findTemplateByName(String name);
}
