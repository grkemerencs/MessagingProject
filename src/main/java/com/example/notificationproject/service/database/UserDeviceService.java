package com.example.notificationproject.service.database;

import com.example.notificationproject.Model.dto.respond.UserDeviceRespondDTO;
import com.example.notificationproject.Model.entity.UserDevice;
import com.example.notificationproject.exception.DataBaseException;
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


    public UserDevice registerUserDevice(UserDevice userDevice){
        try {
            return userDeviceRepository.save(userDevice);
        } catch (Exception e){
            throw new DataBaseException("Error in RegisterUserDevice");
        }
    }

    public UserDevice getUserDeviceById(String id) {
        return userDeviceRepository.findById(id).orElseThrow(() -> new NotFoundException.UserDeviceNotFound(id));
    }

    public List<UserDevice> getUserDeviceById(List<String> deviceIds) {
        return userDeviceRepository.findAllById(deviceIds);
    }

    public UserDevice deleteUserDeviceById(String id) {
        UserDevice userDevice = getUserDeviceById(id);
        userDeviceRepository.delete(userDevice);
        return userDevice;
    }

}
