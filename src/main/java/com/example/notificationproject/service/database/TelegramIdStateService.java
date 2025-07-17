package com.example.notificationproject.service.database;

import com.example.notificationproject.entity.TelegramIdState;
import com.example.notificationproject.repository.TelegramIdStateRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class TelegramIdStateService {
    private final TelegramIdStateRepository telegramIdStateRepository;

    public long getState() {
        return telegramIdStateRepository.findById("singleton")
                .map(TelegramIdState::getState)
                .orElse(0L);
    }


    public void setState(long newState) {
        TelegramIdState state = telegramIdStateRepository.findById("singleton")
                .orElse(new TelegramIdState("singleton", newState)); // yoksa oluştur
        state.setState(newState);
        telegramIdStateRepository.save(state);
    }
}
