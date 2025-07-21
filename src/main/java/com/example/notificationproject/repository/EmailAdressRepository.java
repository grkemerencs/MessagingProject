package com.example.notificationproject.repository;

import com.example.notificationproject.Model.entity.EmailAdress;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailAdressRepository extends MongoRepository<EmailAdress, String> {

}
