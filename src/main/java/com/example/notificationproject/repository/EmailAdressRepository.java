package com.example.notificationproject.repository;

import com.example.notificationproject.Model.entity.EmailAddress;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmailAdressRepository extends MongoRepository<EmailAddress, String> {
    Optional<EmailAddress> findTemplateByEmailAddress(String emailAddress);

}
