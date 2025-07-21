package com.example.notificationproject.repository;

import com.example.notificationproject.entity.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
} 