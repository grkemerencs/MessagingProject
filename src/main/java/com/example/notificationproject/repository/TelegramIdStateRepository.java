package com.example.notificationproject.repository;

import com.example.notificationproject.entity.Email;
import com.example.notificationproject.entity.TelegramIdState;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TelegramIdStateRepository extends MongoRepository<TelegramIdState, String> {
}
