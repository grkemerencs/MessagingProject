package com.example.notificationproject.service.database;

import com.example.notificationproject.Model.entity.TelegramUpdateIdState;
import com.example.notificationproject.repository.TelegramUpdateIdStateRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class TelegramIdStateService {
    private final TelegramUpdateIdStateRepository telegramUpdateIdStateRepository;

    public long getState() {
        return telegramUpdateIdStateRepository.findById("singleton")
                .map(TelegramUpdateIdState::getState)
                .orElse(0L);
    }


    public void setState(long newState) {
        TelegramUpdateIdState state = telegramUpdateIdStateRepository.findById("singleton")
                .orElse(new TelegramUpdateIdState("singleton", newState)); // yoksa oluştur
        state.setState(newState);
        telegramUpdateIdStateRepository.save(state);
    }
}
