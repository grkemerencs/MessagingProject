package com.example.notificationproject.service.database;


import com.example.notificationproject.dto.request.RegisterTemplateRequestDTO;
import com.example.notificationproject.dto.respond.TemplateRespondDTO;
import com.example.notificationproject.entity.Telegram;
import com.example.notificationproject.entity.Template;
import com.example.notificationproject.exception.NotFoundException;
import com.example.notificationproject.mapper.TemplateMapper;
import com.example.notificationproject.repository.TelegramRepository;
import com.example.notificationproject.repository.TemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TelegramService {


    @Autowired
    private final TelegramRepository repo;


    public final List<Telegram> getAllTelegrams() {
        return repo.findAll();
    }

    public final Telegram getTelegramEntityById(String id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Template not found with id: " + "id"));
    }

    public final Telegram registerTelegram(Telegram telegram) {
        telegram.setName(telegram.getName().toLowerCase()); // normalize et Unique kullanırken sıkıtnı çıkmasın
        return repo.save(telegram);
    }

    public boolean isChatIdExist(Long chatId) {
        return repo.existsByTelegramId(chatId);
    }
}
