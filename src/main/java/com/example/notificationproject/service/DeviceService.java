package com.example.notificationproject.service;

import com.example.notificationproject.dto.request.RegisterDeviceRequestDTO;
import com.example.notificationproject.dto.respond.DeviceRequestDTO;
import com.example.notificationproject.entity.Device;
import com.example.notificationproject.mapper.DeviceMapper;
import com.example.notificationproject.repository.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public List<DeviceRequestDTO> getAllDevices(){
        return getAllDeviceEntities().stream().map(DeviceMapper.toDTO).toList();
    }

    public DeviceRequestDTO registerDevice(RegisterDeviceRequestDTO registerDeviceRequestDTO){
        Device device = repo.save(DeviceMapper.toEntity.apply(registerDeviceRequestDTO));
        return DeviceMapper.toDTO.apply(device);
    }

    public Device getDeviceEntityById(int id) {
        return repo.findById(id).orElseThrow(()-> new EntityNotFoundException("Device not found with id: \" + id"));
    }
    public final DeviceRequestDTO getDeviceById(int id) {
        return DeviceMapper.toDTO.apply(getDeviceEntityById(id));
    }

    public List<Device> getDeviceEntityById(List<Integer> deviceIds) {
        return repo.findAllById(deviceIds);
    }
    public final List<DeviceRequestDTO> getDeviceById(List<Integer> debiceIds) {
        return getDeviceEntityById(debiceIds).stream().map(DeviceMapper.toDTO).toList();
    }


}
