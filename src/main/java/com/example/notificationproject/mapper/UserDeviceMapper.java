package com.example.notificationproject.mapper;

import com.example.notificationproject.Model.dto.request.RegisterUserDeviceRequestDTO;
import com.example.notificationproject.Model.dto.respond.UserDeviceRespondDTO;
import com.example.notificationproject.Model.entity.UserDevice;

import java.util.function.Function;

public class UserDeviceMapper {

    public static final Function<UserDevice, UserDeviceRespondDTO> toDTO = device -> {
        UserDeviceRespondDTO dto = new UserDeviceRespondDTO();
        dto.setId(device.getId());
        dto.setOwnerName(device.getOwnerName());
        dto.setPlatform(device.getPlatform());
        return dto;
    };

    public static final Function<RegisterUserDeviceRequestDTO, UserDevice> toEntity = registerDevice -> {
        UserDevice userDevice = new UserDevice();
        userDevice.setOwnerName(registerDevice.getOwnerName());
        userDevice.setPlatform(registerDevice.getPlatform());
        userDevice.setFireBaseToken(registerDevice.fireBaseToken);
        return userDevice;
    };
}