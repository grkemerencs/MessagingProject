package com.example.notificationproject.controller;


import com.example.notificationproject.dto.request.RegisterDeviceRequestDTO;
import com.example.notificationproject.dto.respond.DeviceRespondDTO;
import com.example.notificationproject.entity.Telegram;
import com.example.notificationproject.service.database.DeviceService;
import com.example.notificationproject.service.database.TelegramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("telegram")
public class TelegramController {
    private final TelegramService telegramService;


    @GetMapping
    public ResponseEntity<List<Telegram>> getAllTelegrams() {
        return ResponseEntity.ok(telegramService.getAllTelegrams());
    }

    @PostMapping("/register")
    public ResponseEntity<Telegram> registerTelegram(@RequestBody Telegram telegram){
        return ResponseEntity.ok(telegramService.registerTelegram(telegram));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telegram> getTelegramWithId(@PathVariable String id) {
        Telegram telegram = telegramService.getTelegramEntityById(id);
        return ResponseEntity.ok(telegram);
    }
}
