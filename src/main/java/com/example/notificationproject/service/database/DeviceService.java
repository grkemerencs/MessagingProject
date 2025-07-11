package com.example.notificationproject.service.database;

import com.example.notificationproject.dto.request.RegisterDeviceRequestDTO;
import com.example.notificationproject.dto.respond.DeviceRespondDTO;
import com.example.notificationproject.entity.Device;
import com.example.notificationproject.exception.NotFoundException;
import com.example.notificationproject.mapper.DeviceMapper;
import com.example.notificationproject.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceService {
    @Autowired
    private DeviceRepository repo;


    public List<Device> getAllDeviceEntities() {
        return repo.findAll();
    }

    public List<DeviceRespondDTO> getAllDevices(){
        return getAllDeviceEntities().stream().map(DeviceMapper.toDTO).toList();
    }

    public DeviceRespondDTO registerDevice(RegisterDeviceRequestDTO registerDeviceRequestDTO){
        Device device = repo.save(DeviceMapper.toEntity.apply(registerDeviceRequestDTO));
        return DeviceMapper.toDTO.apply(device);
    }

    public Device getDeviceEntityById(String id) {
        return repo.findById(id).orElseThrow(()-> new NotFoundException("Device not found with id: \" + id"));
    }
    public final DeviceRespondDTO getDeviceById(String id) {
        return DeviceMapper.toDTO.apply(getDeviceEntityById(id));
    }

    public List<Device> getDeviceEntityById(List<String> deviceIds) {
        return repo.findAllById(deviceIds);
    }
    public final List<DeviceRespondDTO> getDeviceById(List<String> deviceIds) {
        return getDeviceEntityById(deviceIds).stream().map(DeviceMapper.toDTO).toList();
    }


}
