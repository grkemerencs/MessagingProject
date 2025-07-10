package com.example.notificationproject.controller;


import com.example.notificationproject.dto.respond.DeviceRequestDTO;
import com.example.notificationproject.dto.request.RegisterDeviceRequestDTO;
import com.example.notificationproject.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("devices")
public class DeviceController {

    private final DeviceService deviceService;


    @GetMapping
    public ResponseEntity<List<DeviceRequestDTO>> getAllDevices() {
        List<DeviceRequestDTO> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/register")
    public ResponseEntity<DeviceRequestDTO> registerDevice(@Valid @RequestBody RegisterDeviceRequestDTO registerDeviceRequestDTO){
        DeviceRequestDTO deviceRequestDTO = deviceService.registerDevice(registerDeviceRequestDTO);
        return ResponseEntity.ok(deviceRequestDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceRequestDTO> getDeviceWithId(@PathVariable int id) {
        DeviceRequestDTO deviceRequestDTO = deviceService.getDeviceById(id);
        return ResponseEntity.ok(deviceRequestDTO);
    }

}
