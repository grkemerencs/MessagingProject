package com.example.notificationproject.service;

import com.example.notificationproject.dto.TelegramUpdateDTO;
import com.example.notificationproject.entity.Telegram;
import com.example.notificationproject.repository.TelegramRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TelegramSaveUsersToDatabaseServiceTest {
    @Mock
    private TelegramRepository telegramRepository;

    @Mock
    private TelegramGetUpdateService telegramGetUpdateService;

    @InjectMocks
    private TelegramSaveUsersToDatabaseService telegramSaveUsersToDatabaseService;

    @Test
    void testSaveUser() throws Exception {
        TelegramUpdateDTO existingDto = new TelegramUpdateDTO("User", 123L);
        TelegramUpdateDTO newDto = new TelegramUpdateDTO("NewUser", 456L);
        List<TelegramUpdateDTO> dtoList = List.of(existingDto, newDto);

        when(telegramGetUpdateService.getLastTelegramUpdateDTO()).thenReturn(dtoList);
        when(telegramRepository.existsByTelegramId(123L)).thenReturn(true);
        when(telegramRepository.existsByTelegramId(456L)).thenReturn(false);
        Method method = TelegramSaveUsersToDatabaseService.class.getDeclaredMethod("saveUsersToDatabase");
        method.setAccessible(true);
        method.invoke(telegramSaveUsersToDatabaseService);

        Telegram expectedTelegram = new Telegram();
        expectedTelegram.setTelegramId(456L);
        expectedTelegram.setName("NewUser");
        verify(telegramRepository).saveAll(List.of(expectedTelegram));
    }


} 