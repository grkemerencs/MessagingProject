package com.example.notificationproject.service.database;

import com.example.notificationproject.Model.dto.request.EmailAddressRegisterRequestDTO;
import com.example.notificationproject.Model.entity.EmailAddress;
import com.example.notificationproject.exception.MongoDuplicateIndexException;
import com.example.notificationproject.exception.NotFoundException;
import com.example.notificationproject.repository.EmailAdressRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailAddressService {
    private final EmailAdressRepository emailAdressRepository;

    public EmailAddressService(EmailAdressRepository emailAdressRepository) {
        this.emailAdressRepository = emailAdressRepository;
    }

    public EmailAddress save(EmailAddress emailAddress) {
        try {
            return emailAdressRepository.save(emailAddress);
        } catch (DuplicateKeyException ex) {
            throw new MongoDuplicateIndexException( "Email already exists: " + emailAddress.getEmailAddress());
        }
    }

    public EmailAddress registerEmail(EmailAddressRegisterRequestDTO emailAddressRegisterRequestDTO){
        EmailAddress email = new EmailAddress();
        email.setEmailAddress(emailAddressRegisterRequestDTO.getEmailAddress());
        return save(email);
    }

    public List<EmailAddress> getAllEmailAddresses() {
        return emailAdressRepository.findAll();
    }

    public EmailAddress getEmailAddressById(String id) {
        return emailAdressRepository.findById(id).orElseThrow(()-> new NotFoundException.EmailAddressNotFound(id));
    }
    public List<EmailAddress> getEmailAddressById(List<String> ids) {
        return emailAdressRepository.findAllById(ids);
    }

    public EmailAddress getEmailAddressByEmailAddress(String emailAddress) {
        return emailAdressRepository.findTemplateByEmailAddress(emailAddress).orElseThrow(()-> new NotFoundException.EmailAddressNotFound(emailAddress));
    }

    public EmailAddress deleteEmailAddressByEmailAddress(String emailAddress) {
        EmailAddress email = getEmailAddressByEmailAddress(emailAddress);
        emailAdressRepository.delete(email);
        return email;
    }

    public EmailAddress deleteEmailAddressById(String id) {
        EmailAddress email = getEmailAddressById(id);
        emailAdressRepository.delete(email);
        return email;
    }

}
