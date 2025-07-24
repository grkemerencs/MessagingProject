package com.example.notificationproject.service.database;


import com.example.notificationproject.Model.entity.UserTelegramAccount;
import com.example.notificationproject.exception.DataBaseException;
import com.example.notificationproject.exception.NotFoundException;
import com.example.notificationproject.repository.UserTelegramAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserTelegramAccountService {


    @Autowired
    private final UserTelegramAccountRepository repo;


    public final List<UserTelegramAccount> getAllUserTelegramAccounts() {
        return repo.findAll();
    }

    public final UserTelegramAccount getUserTelegramAccountById(String id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException.UserTelegramAccountNotFound(id));
    }

    public final UserTelegramAccount registerUserTelegramAccount(UserTelegramAccount userTelegramAccount) {
        try {
            userTelegramAccount.setName(userTelegramAccount.getName().toLowerCase());
            return repo.save(userTelegramAccount);
        } catch (Exception e) {
            throw new DataBaseException("Error in registerUserTelegramAccount");
        }
    }

    public boolean isChatIdExist(Long chatId) {
        return repo.existsByTelegramId(chatId);
    }

    public UserTelegramAccount deleteUserTelegramAccountByTelegramId(String telegramId) {
        UserTelegramAccount userTelegramAccount = getUserTelegramAccountById(telegramId);
        repo.deleteById(telegramId);
        return userTelegramAccount;
    }
}
