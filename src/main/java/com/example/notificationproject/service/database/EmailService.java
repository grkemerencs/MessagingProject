package com.example.notificationproject.service.database;

import com.example.notificationproject.dto.request.RegisterEmailRequestDTO;
import com.example.notificationproject.entity.Email;
import com.example.notificationproject.exception.NotFoundException;
import com.example.notificationproject.repository.EmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailService {
    @Autowired
    private EmailRepository repo;


    public List<Email> getAllEmailEntities() {
        return repo.findAll();
    }

    public Email registerEmail(RegisterEmailRequestDTO registerEmailRequestDTO){
        Email email = new Email();
        email.setEmailAdress(registerEmailRequestDTO.getEmailAdress());
        return repo.save(email);
    }

    public Email getEmailEntityById(String id) {
        return repo.findById(id).orElseThrow(()-> new NotFoundException("Email not found with id: \" + id"));
    }
    public List<Email> getEmailEntityById(List<String> ids) {
        return repo.findAllById(ids);
    }

}
