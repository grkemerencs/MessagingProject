package com.example.notificationproject.controller;


import com.example.notificationproject.Model.entity.UserTelegramAccount;
import com.example.notificationproject.service.database.UserTelegramAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("telegram")
public class UserTelegramAccountController {
    private final UserTelegramAccountService userTelegramAccountService;


    @GetMapping
    public ResponseEntity<List<UserTelegramAccount>> getAllUserTelegramAccounts() {
        return ResponseEntity.ok(userTelegramAccountService.getAllUserTelegramAccounts());
    }

    @PostMapping("/register")
    public ResponseEntity<UserTelegramAccount> registerUserTelegramAccount(@RequestBody UserTelegramAccount userTelegramAccount){
        return ResponseEntity.ok(userTelegramAccountService.registerUserTelegramAccount(userTelegramAccount));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTelegramAccount> getUserTelegramAccountWithId(@PathVariable String id) {
        UserTelegramAccount userTelegramAccount = userTelegramAccountService.getUserTelegramAccountById(id);
        return ResponseEntity.ok(userTelegramAccount);
    }
}
