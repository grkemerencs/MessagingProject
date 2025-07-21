package com.example.notificationproject.repository;

import com.example.notificationproject.Model.entity.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
} 