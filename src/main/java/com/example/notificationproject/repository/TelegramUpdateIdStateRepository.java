package com.example.notificationproject.repository;

import com.example.notificationproject.Model.entity.TelegramUpdateIdState;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TelegramUpdateIdStateRepository extends MongoRepository<TelegramUpdateIdState, String> {
}
