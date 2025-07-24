package com.example.notificationproject.controller;


import com.example.notificationproject.Model.entity.UserTelegramAccount;
import com.example.notificationproject.Model.dto.respond.ApiRespondDTO;
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
    public ResponseEntity<ApiRespondDTO<List<UserTelegramAccount>>> getAllUserTelegramAccounts() {
        return ResponseEntity.ok(new ApiRespondDTO<>(userTelegramAccountService.getAllUserTelegramAccounts()));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiRespondDTO<UserTelegramAccount>> registerUserTelegramAccount(@RequestBody UserTelegramAccount userTelegramAccount){
        userTelegramAccount.setId(null);
        return ResponseEntity.ok(new ApiRespondDTO<>(userTelegramAccountService.registerUserTelegramAccount(userTelegramAccount)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRespondDTO<UserTelegramAccount>> getUserTelegramAccountWithId(@PathVariable String id) {
        UserTelegramAccount userTelegramAccount = userTelegramAccountService.getUserTelegramAccountById(id);
        return ResponseEntity.ok(new ApiRespondDTO<>(userTelegramAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiRespondDTO<UserTelegramAccount>> deleteUserTelegramAccountWithId(@PathVariable String id) {
        UserTelegramAccount userTelegramAccount = userTelegramAccountService.deleteUserTelegramAccountByTelegramId(id);
        return ResponseEntity.ok(new ApiRespondDTO<>(userTelegramAccount));
    }

}
