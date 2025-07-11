package com.example.notificationproject.service.messaging;

import com.example.notificationproject.dto.request.NotificationRequestDTO;
import com.example.notificationproject.entity.Email;
import com.example.notificationproject.service.database.DeviceService;
import com.example.notificationproject.service.database.EmailService;
import com.example.notificationproject.service.MessageConstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmailMessagingService implements BaseMessagingService {

    public final EmailService emailService;
    private final JavaMailSender javaMailSender;
    private final MessageConstructorService messageConstructorService;

    @Override
    public String sendNotifications(NotificationRequestDTO notificationRequestDTO) {
        Set<String> allEmails = new HashSet<>();
        allEmails.addAll(notificationRequestDTO.getEmails());
        List<String> emailRequestsByIds = notificationRequestDTO.getEmailIds();
        allEmails.addAll(emailService
                        .getEmailEntityById(
                        notificationRequestDTO.getDeviceIds()).stream().map(Email::getEmailAdress).toList()
                        );
        System.out.println(notificationRequestDTO.getEmailIds());
        System.out.println(Arrays.toString(allEmails.toArray()));
        SimpleMailMessage message = messageConstructorService.constructSimpleMailMessage(notificationRequestDTO);
        int failedEmailMessages = 0;
        for (String to : allEmails) {
            try {
                message.setTo(to);
                javaMailSender.send(message);
            } catch (Exception e) {
                System.err.println("Mail gönderilemedi: " + to + " Hata: " + e.getMessage());
                failedEmailMessages++;
            }
        }
        return (allEmails.size()-failedEmailMessages)+"Başarılı, "+failedEmailMessages+" Başarısız Email Gönderildi";
    }

    @Override
    public String sendNotificationsToAll(NotificationRequestDTO notificationRequestDTO) {
        List<Email> emails = emailService.getAllEmailEntities();
        List<String> emailsAsString = emails.stream().map(Email::getEmailAdress).toList();
        notificationRequestDTO.setEmails(emailsAsString);
        return sendNotifications(notificationRequestDTO);
    }
}
