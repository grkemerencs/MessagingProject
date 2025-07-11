package com.example.notificationproject.repository;

import com.example.notificationproject.entity.Template;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemplateRepository extends MongoRepository<Template, String> {
}
