package com.example.notificationproject.service;

import com.example.notificationproject.Model.dto.UserTelegramAccountUpdateDTO;
import com.example.notificationproject.Model.entity.UserTelegramAccount;
import com.example.notificationproject.repository.UserTelegramAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserTelegramAccountSaveUsersToDatabaseServiceTest {
    @Mock
    private UserTelegramAccountRepository userTelegramAccountRepository;

    @Mock
    private TelegramGetUpdateService telegramGetUpdateService;

    @InjectMocks
    private TelegramSaveUsersToDatabaseService telegramSaveUsersToDatabaseService;

    @Test
    void testSaveUser() throws Exception {
        UserTelegramAccountUpdateDTO existingDto = new UserTelegramAccountUpdateDTO("User", 123L);
        UserTelegramAccountUpdateDTO newDto = new UserTelegramAccountUpdateDTO("NewUser", 456L);
        List<UserTelegramAccountUpdateDTO> dtoList = List.of(existingDto, newDto);

        when(telegramGetUpdateService.getLastTelegramUpdateDTO()).thenReturn(dtoList);
        when(userTelegramAccountRepository.existsByTelegramId(123L)).thenReturn(true);
        when(userTelegramAccountRepository.existsByTelegramId(456L)).thenReturn(false);
        Method method = TelegramSaveUsersToDatabaseService.class.getDeclaredMethod("saveUsersToDatabase");
        method.setAccessible(true);
        method.invoke(telegramSaveUsersToDatabaseService);

        UserTelegramAccount expectedUserTelegramAccount = new UserTelegramAccount();
        expectedUserTelegramAccount.setTelegramId(456L);
        expectedUserTelegramAccount.setName("NewUser");
        verify(userTelegramAccountRepository).saveAll(List.of(expectedUserTelegramAccount));
    }


} 