package com.example.notificationproject.mapper;

import com.example.notificationproject.Model.dto.request.UserDeviceRegisterRequestDTO;
import com.example.notificationproject.Model.dto.respond.UserDeviceRespondDTO;
import com.example.notificationproject.Model.entity.UserDevice;
import org.mapstruct.Mapper;

@Mapper
public interface UserDeviceMapper {
    UserDeviceRespondDTO toDTO(UserDevice userDevice);
    UserDevice toEntity(UserDeviceRegisterRequestDTO userDeviceRegisterRequestDTO);

}