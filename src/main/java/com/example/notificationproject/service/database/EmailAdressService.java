package com.example.notificationproject.service.database;

import com.example.notificationproject.Model.dto.request.RegisterEmailAdressRequestDTO;
import com.example.notificationproject.Model.entity.EmailAdress;
import com.example.notificationproject.exception.NotFoundException;
import com.example.notificationproject.repository.EmailAdressRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailAdressService {
    @Autowired
    private EmailAdressRepository repo;


    public List<EmailAdress> getAllEmailAdresses() {
        return repo.findAll();
    }

    public EmailAdress registerEmail(RegisterEmailAdressRequestDTO registerEmailAdressRequestDTO){
        EmailAdress email = new EmailAdress();
        email.setEmailAdress(registerEmailAdressRequestDTO.getEmailAdress());
        return repo.save(email);
    }

    public EmailAdress getEmailAdressById(String id) {
        return repo.findById(id).orElseThrow(()-> new NotFoundException("EmailAdress not found with id: " + id));
    }
    public List<EmailAdress> getEmailAdressById(List<String> ids) {
        return repo.findAllById(ids);
    }

}
