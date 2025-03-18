package com.example.iotproject.service;
import com.example.iotproject.dto.DeviceDTO;
import com.example.iotproject.model.DeviceAction;
import com.example.iotproject.dao.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;

    // Phương thức lưu hành động của thiết bị vào DB
    public void logDeviceAction(DeviceDTO deviceActionDTO) {
        DeviceAction deviceAction = new DeviceAction();
        deviceAction.setDevice(deviceActionDTO.getDevice());
        deviceAction.setAction(deviceActionDTO.getAction());
        deviceAction.setTimestamp(deviceActionDTO.getTimestamp());

        // Lưu vào cơ sở dữ liệu
        deviceDAO.save(deviceAction);
    }


    public List<DeviceDTO> getAllDeviceActions() {
        List<DeviceAction> deviceActions = deviceDAO.findAll();

        // Chuyển Entity sang DTO
        return deviceActions.stream()
                .map(deviceAction -> new DeviceDTO(
                        deviceAction.getId(),
                        deviceAction.getDevice(),
                        deviceAction.getAction(),
                        deviceAction.getTimestamp()))
                .collect(Collectors.toList());
    }
}
