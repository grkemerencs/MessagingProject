package com.example.notificationproject.repository;

import com.example.notificationproject.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Integer> {
}
