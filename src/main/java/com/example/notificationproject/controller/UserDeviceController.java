package com.example.notificationproject.controller;


import com.example.notificationproject.Model.dto.request.UserDeviceRegisterRequestDTO;
import com.example.notificationproject.Model.dto.respond.ApiRespondDTO;
import com.example.notificationproject.Model.dto.respond.UserDeviceRespondDTO;
import com.example.notificationproject.Model.entity.UserDevice;
import com.example.notificationproject.mapper.UserDeviceMapper;
import com.example.notificationproject.service.database.UserDeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("device")
public class UserDeviceController {

    private final UserDeviceService userDeviceService;
    private final UserDeviceMapper userDeviceMapper = Mappers.getMapper(UserDeviceMapper.class);


    @GetMapping
    public ResponseEntity<ApiRespondDTO<List<UserDeviceRespondDTO>>> getAllUserDevices() {
        List<UserDevice> userDevices = userDeviceService.getAllUserDevices();
        return ResponseEntity.ok(new ApiRespondDTO<>(userDevices.stream().map(userDeviceMapper::toDTO).toList()));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiRespondDTO<UserDeviceRespondDTO>> registerUserDevice(@Valid @RequestBody UserDeviceRegisterRequestDTO userDeviceRegisterRequestDTO){
        UserDevice userDevice = userDeviceService.registerUserDevice(userDeviceMapper.toEntity(userDeviceRegisterRequestDTO));
        return ResponseEntity.ok(new ApiRespondDTO<>(userDeviceMapper.toDTO(userDevice)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRespondDTO<UserDeviceRespondDTO>> getUserDeviceWithId(@PathVariable String id) {
        UserDevice userDevice = userDeviceService.getUserDeviceById(id);
        return ResponseEntity.ok(new ApiRespondDTO<>(userDeviceMapper.toDTO(userDevice)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiRespondDTO<UserDeviceRespondDTO>> deleteUserDevice(@PathVariable String id) {
        UserDevice userDevice = userDeviceService.deleteUserDeviceById(id);
        return ResponseEntity.ok(new ApiRespondDTO<>(userDeviceMapper.toDTO(userDevice)));
    }

}
