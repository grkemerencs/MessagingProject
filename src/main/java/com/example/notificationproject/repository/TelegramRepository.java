package com.example.notificationproject.repository;

import com.example.notificationproject.entity.Email;
import com.example.notificationproject.entity.Telegram;
import com.example.notificationproject.entity.TelegramIdState;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TelegramRepository extends MongoRepository<Telegram, String> {
    Optional<Telegram> findByTelegramId(Long telegramId);
    boolean existsByTelegramId(Long telegramId);

    void deleteByTelegramId(Long telegramId);
}
