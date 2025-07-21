package com.example.notificationproject.repository;

import com.example.notificationproject.Model.entity.UserTelegramAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserTelegramAccountRepository extends MongoRepository<UserTelegramAccount, String> {
    Optional<UserTelegramAccount> findByTelegramId(Long telegramId);
    boolean existsByTelegramId(Long telegramId);

    void deleteByTelegramId(Long telegramId);
}
