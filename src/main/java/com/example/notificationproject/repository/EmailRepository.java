package com.example.notificationproject.repository;

import com.example.notificationproject.entity.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<Email, String> {
}
