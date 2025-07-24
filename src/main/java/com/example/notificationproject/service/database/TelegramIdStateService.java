package com.example.notificationproject.service.database;

import com.example.notificationproject.Model.entity.TelegramUpdateIdState;
import com.example.notificationproject.exception.DataBaseException;
import com.example.notificationproject.repository.TelegramUpdateIdStateRepository;
import lombok.Data;
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
        try {
            return telegramUpdateIdStateRepository.findById("singleton")
                    .map(TelegramUpdateIdState::getState)
                    .orElse(0L);
        } catch (Exception ex) {
            throw new DataBaseException("Error in Accessing telegramUpdateIdStateRepository");
        }
    }


    public void setState(long newState) {
        try {
            TelegramUpdateIdState state = telegramUpdateIdStateRepository.findById("singleton")
                    .orElse(new TelegramUpdateIdState("singleton", newState)); // yoksa oluştur
            state.setState(newState);
            telegramUpdateIdStateRepository.save(state);
        } catch (Exception ex) {
            throw new DataBaseException("Error in Setting telegramUpdateIdState");
        }

    }
}
