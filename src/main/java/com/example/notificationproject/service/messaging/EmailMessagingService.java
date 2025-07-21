package com.example.notificationproject.service.messaging;

import com.example.notificationproject.Model.Aggregate.NotificationRequest;
import com.example.notificationproject.Model.entity.EmailAdress;
import com.example.notificationproject.service.database.EmailAdressService;
import com.example.notificationproject.service.MessageConstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmailMessagingService implements BaseMessagingService {

    public final EmailAdressService emailAdressService;
    private final JavaMailSender javaMailSender;
    private final MessageConstructorService messageConstructorService;

    @Override
    public String sendNotifications(NotificationRequest notificationRequest) {
        Set<String> allEmails = new HashSet<>();
        allEmails.addAll(notificationRequest.getEmails());
        List<String> emailRequestsByIds = notificationRequest.getEmailIds();
        allEmails.addAll(emailAdressService
                        .getEmailAdressById(
                        notificationRequest.getEmailIds()).stream().map(EmailAdress::getEmailAdress).toList()
                        );
        SimpleMailMessage message = messageConstructorService.constructSimpleMailMessage(notificationRequest);
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
        return (allEmails.size()-failedEmailMessages)+" Başarılı --- , "+failedEmailMessages+" Başarısız Email Gönderildi";
    }

    @Override
    public String sendNotificationsToAll(NotificationRequest notificationRequest) {
        List<EmailAdress> emails = emailAdressService.getAllEmailAdresses();
        List<String> emailsAsString = emails.stream().map(EmailAdress::getEmailAdress).toList();
        notificationRequest.setEmails(emailsAsString);
        return sendNotifications(notificationRequest);
    }
}
