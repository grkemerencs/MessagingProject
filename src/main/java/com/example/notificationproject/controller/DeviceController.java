package com.example.notificationproject.controller;


import com.example.notificationproject.dto.respond.DeviceRespondDTO;
import com.example.notificationproject.dto.request.RegisterDeviceRequestDTO;
import com.example.notificationproject.service.database.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("device")
public class DeviceController {

    private final DeviceService deviceService;


    @GetMapping
    public ResponseEntity<List<DeviceRespondDTO>> getAllDevices() {
        List<DeviceRespondDTO> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/register")
    public ResponseEntity<DeviceRespondDTO> registerDevice(@Valid @RequestBody RegisterDeviceRequestDTO registerDeviceRequestDTO){
        DeviceRespondDTO deviceRespondDTO = deviceService.registerDevice(registerDeviceRequestDTO);
        return ResponseEntity.ok(deviceRespondDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceRespondDTO> getDeviceWithId(@PathVariable String id) {
        DeviceRespondDTO deviceRespondDTO = deviceService.getDeviceById(id);
        return ResponseEntity.ok(deviceRespondDTO);
    }

}
