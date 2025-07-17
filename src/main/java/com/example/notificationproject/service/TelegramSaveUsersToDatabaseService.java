package com.example.notificationproject.service;

import com.example.notificationproject.dto.TelegramUpdateDTO;
import com.example.notificationproject.entity.Telegram;
import com.example.notificationproject.repository.TelegramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramSaveUsersToDatabaseService {
    private final TelegramGetUpdateService telegramGetUpdateService;
    private final TelegramRepository telegramRepository;


    @Scheduled(fixedDelay = 10000)
    private void saveUsersToDatabase(){
        List<TelegramUpdateDTO> telegramUpdateDTOList = telegramGetUpdateService.getLastTelegramUpdateDTO();
        if(telegramUpdateDTOList == null) return;
        List<Telegram> telegramList = new ArrayList<>();
        for(TelegramUpdateDTO telegramUpdateDTO: telegramUpdateDTOList) {
            if(telegramRepository.existsByTelegramId(telegramUpdateDTO.getChatId())) continue;
            Telegram telegram = new Telegram();
            telegram.setTelegramId(telegramUpdateDTO.getChatId());
            telegram.setName(telegramUpdateDTO.getFromName());
            telegramList.add(telegram);
        }
        telegramRepository.saveAll(telegramList);
    }


}
