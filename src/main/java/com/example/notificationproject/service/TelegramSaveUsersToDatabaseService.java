package com.example.notificationproject.service;

import com.example.notificationproject.Model.dto.UserTelegramAccountUpdateDTO;
import com.example.notificationproject.Model.entity.UserTelegramAccount;
import com.example.notificationproject.repository.UserTelegramAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramSaveUsersToDatabaseService {
    private final TelegramGetUpdateService telegramGetUpdateService;
    private final UserTelegramAccountRepository userTelegramAccountRepository;


    @Scheduled(fixedDelay = 10000)
    private void saveUsersToDatabase(){
        List<UserTelegramAccountUpdateDTO> userTelegramAccountUpdateDTOList = telegramGetUpdateService.getLastTelegramUpdateDTO();
        if(userTelegramAccountUpdateDTOList == null) return;
        List<UserTelegramAccount> userTelegramAccountList = new ArrayList<>();
        for(UserTelegramAccountUpdateDTO userTelegramAccountUpdateDTO : userTelegramAccountUpdateDTOList) {
            if(userTelegramAccountRepository.existsByTelegramId(userTelegramAccountUpdateDTO.getChatId())) continue;
            UserTelegramAccount userTelegramAccount = new UserTelegramAccount();
            userTelegramAccount.setTelegramId(userTelegramAccountUpdateDTO.getChatId());
            userTelegramAccount.setName(userTelegramAccountUpdateDTO.getFromName());
            userTelegramAccountList.add(userTelegramAccount);
        }
        if(userTelegramAccountList.isEmpty()) return;
        userTelegramAccountRepository.saveAll(userTelegramAccountList);
        log.info("New Telegram Accounts added to DB = {}", Arrays.toString(userTelegramAccountList.stream().map(UserTelegramAccount::toString).toArray()));
    }


}
