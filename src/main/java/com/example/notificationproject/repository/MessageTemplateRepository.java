package com.example.notificationproject.repository;

import com.example.notificationproject.Model.entity.MessageTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MessageTemplateRepository extends MongoRepository<MessageTemplate, String> {
    Optional<MessageTemplate> findTemplateByName(String name);
}
