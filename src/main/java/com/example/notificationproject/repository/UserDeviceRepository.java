package com.example.notificationproject.repository;

import com.example.notificationproject.Model.entity.UserDevice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDeviceRepository extends MongoRepository<UserDevice, String> {
}
