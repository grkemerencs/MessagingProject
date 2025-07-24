package com.example.notificationproject.service.messaging;

import com.example.notificationproject.Model.Aggregate.NotificationRequest;
import com.example.notificationproject.Model.entity.EmailAddress;
import com.example.notificationproject.service.database.EmailAddressService;
import com.example.notificationproject.service.MessageConstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EmailMessagingService implements BaseMessagingService {

    public final EmailAddressService emailAddressService;
    private final JavaMailSender javaMailSender;
    private final MessageConstructorService messageConstructorService;

    @Override
    public String sendNotifications(NotificationRequest notificationRequest) {
        Set<String> allEmails = new HashSet<>();
        allEmails.addAll(notificationRequest.getEmails());
        List<String> emailRequestsByIds = notificationRequest.getEmailIds();
        allEmails.addAll(emailAddressService
                        .getEmailAddressById(
                        notificationRequest.getEmailIds()).stream().map(EmailAddress::getEmailAddress).toList()
                        );
        SimpleMailMessage message = messageConstructorService.constructSimpleMailMessage(notificationRequest);
        List<String> failedEmailMessages = new ArrayList<>();
        List<String> successfulEmailMessages = new ArrayList<>();
        for (String to : allEmails) {
            try {
                message.setTo(to);
                javaMailSender.send(message);
                successfulEmailMessages.add(to);
            } catch (Exception e) {
                failedEmailMessages.add(to);
            }
        }
        return "Message successfully delivered To: "+ Arrays.toString(successfulEmailMessages.toArray())+
                "------Message failed to: "+Arrays.toString(failedEmailMessages.toArray());
    }

    @Override
    public String sendNotificationsToAll(NotificationRequest notificationRequest) {
        List<EmailAddress> emails = emailAddressService.getAllEmailAddresses();
        List<String> emailsAsString = emails.stream().map(EmailAddress::getEmailAddress).toList();
        notificationRequest.setEmails(emailsAsString);
        return sendNotifications(notificationRequest);
    }
}
