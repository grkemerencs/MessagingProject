package com.example.notificationproject.service.messaging;

import com.example.notificationproject.dto.request.NotificationRequestDTO;
import com.example.notificationproject.entity.Email;
import com.example.notificationproject.service.MessageConstructorService;
import com.example.notificationproject.service.database.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailMessagingServiceTest {
    @Mock
    private EmailService emailService;
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private MessageConstructorService messageConstructorService;

    @InjectMocks
    private EmailMessagingService emailMessagingService;

    @Test
    void testSendNotifications_success() {
        // Arrange
        NotificationRequestDTO dto = new NotificationRequestDTO();
        dto.setEmails(List.of("test1@gmail.com"));
        dto.setEmailIds(List.of());

        List<Email> idList = new ArrayList<>(List.of(
                new Email("123","test2@adasf.com"),
                new Email("145","test1@gmail.com"))); // aynı emailden 2 tane almıcak

        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("merhabalar");
        message.setTo("");
        when(messageConstructorService.constructSimpleMailMessage(any())).thenReturn(message);
        when(emailService.getEmailEntityById(anyList())).thenReturn(idList);
        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class)); // hiçbirşey yapmasın başarılı*

        // Act
        String result = emailMessagingService.sendNotifications(dto);

        // Assert
        verify(javaMailSender,times(2)).send(any(SimpleMailMessage.class)); // 2 kere çalıştı
    }

    @Test
    void testSendNotificationsToAll_success() {
        // Arrange
        NotificationRequestDTO dto = new NotificationRequestDTO();
        dto.setEmails(List.of("test1@gmail.com")); // önemi yok
        dto.setEmailIds(List.of());

        List<Email> idList = new ArrayList<>(List.of(
                new Email("123","test2@adasf.com"),
                new Email("145","test3@gmail.com"))); // 3 farklı mail 2 si databaseden geliyor

        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("merhabalar");
        message.setTo("");
        when(messageConstructorService.constructSimpleMailMessage(any())).thenReturn(message);
        when(emailService.getEmailEntityById(anyList())).thenReturn(idList);
        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class)); // hiçbirşey yapmasın başarılı*

        // Act
        String result = emailMessagingService.sendNotificationsToAll(dto);

        // Assert
        verify(javaMailSender,times(2)).send(any(SimpleMailMessage.class)); // 2 kere çalıştı
    }
} 