package com.example.notificationproject.service.database;

import com.example.notificationproject.Model.dto.respond.UserDeviceRespondDTO;
import com.example.notificationproject.Model.entity.UserDevice;
import com.example.notificationproject.exception.NotFoundException;
import com.example.notificationproject.mapper.UserDeviceMapper;
import com.example.notificationproject.repository.UserDeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDeviceService {
    @Autowired
    private UserDeviceRepository userDeviceRepository;


    public List<UserDevice> getAllUserDevices() {
        return userDeviceRepository.findAll();
    }

    public List<UserDevice> getAllDevices(){
        return getAllUserDevices();
    }

    public UserDevice registerUserDevice(UserDevice userDevice){
        return userDeviceRepository.save(userDevice);
    }

    public UserDevice getUserDeviceById(String id) {
        return userDeviceRepository.findById(id).orElseThrow(NotFoundException.DeviceNotFound::new);
    }
    public final UserDevice getDeviceById(String id) {
        return getUserDeviceById(id);
    }

    public List<UserDevice> getUserDeviceById(List<String> deviceIds) {
        return userDeviceRepository.findAllById(deviceIds);
    }
    public final List<UserDevice> getDeviceById(List<String> deviceIds) {
        return getUserDeviceById(deviceIds);
    }


}
