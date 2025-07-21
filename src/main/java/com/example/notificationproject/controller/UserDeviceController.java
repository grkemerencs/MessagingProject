package com.example.notificationproject.controller;


import com.example.notificationproject.Model.dto.request.RegisterUserDeviceRequestDTO;
import com.example.notificationproject.Model.entity.UserDevice;
import com.example.notificationproject.mapper.UserDeviceMapper;
import com.example.notificationproject.service.database.UserDeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("device")
public class UserDeviceController {

    private final UserDeviceService userDeviceService;


    @GetMapping
    public ResponseEntity<List<UserDevice>> getAllUserDevices() {
        List<UserDevice> userDevices = userDeviceService.getAllUserDevices();
        return ResponseEntity.ok(userDevices);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDevice> registerUserDevice(@Valid @RequestBody RegisterUserDeviceRequestDTO registerUserDeviceRequestDTO){
        UserDevice userDevice = userDeviceService.registerUserDevice(UserDeviceMapper.toEntity.apply(registerUserDeviceRequestDTO));
        return ResponseEntity.ok(userDevice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDevice> getUserDeviceWithId(@PathVariable String id) {
        UserDevice userDevice = userDeviceService.getUserDeviceById(id);
        return ResponseEntity.ok(userDevice);
    }

}
