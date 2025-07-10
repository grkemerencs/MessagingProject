package com.example.notificationproject.mapper;

import com.example.notificationproject.dto.request.RegisterDeviceRequestDTO;
import com.example.notificationproject.dto.respond.DeviceRequestDTO;
import com.example.notificationproject.entity.Device;

import java.util.function.Function;

public class DeviceMapper {

    public static final Function<Device, DeviceRequestDTO> toDTO = device -> {
        DeviceRequestDTO dto = new DeviceRequestDTO();
        dto.setId(device.getId());
        dto.setOwnerName(device.getOwnerName());
        dto.setPlatform(device.getPlatform());
        return dto;
    };

    public static final Function<RegisterDeviceRequestDTO, Device> toEntity = registerDevice -> {
        Device device = new Device();
        device.setOwnerName(registerDevice.getOwnerName());
        device.setPlatform(registerDevice.getPlatform());
        device.setFireBaseToken(registerDevice.fireBaseToken);
        return device;
    };
}