package com.example.iotproject.controller;
import com.example.iotproject.dto.DeviceDTO;
import com.example.iotproject.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;


    @PostMapping("/toggle-device")
    public String toggleLight(@RequestBody DeviceDTO deviceActionDTO) {
        // In thông tin nhận được từ client (kiểm tra trong console)
        System.out.println("Received device: " + deviceActionDTO.getDevice());
        System.out.println("Received action: " + deviceActionDTO.getAction());
        System.out.println("Received timestamp: " + deviceActionDTO.getTimestamp());

        // Lưu hành động vào cơ sở dữ liệu
        deviceService.logDeviceAction(deviceActionDTO);

        // Trả về phản hồi cho người dùng
        return "Light is now " + deviceActionDTO.getAction();
    }


    // API lấy tất cả dữ liệu DeviceAction
    @GetMapping("/device-actions")
    public List<DeviceDTO> getAllDeviceActions() {
        // Lấy tất cả dữ liệu DeviceAction
        List<DeviceDTO> allDeviceActions = deviceService.getAllDeviceActions();

        // Sắp xếp theo ID giảm dần (ID lớn hơn là mới nhất)
        allDeviceActions.sort((a, b) -> Long.compare(b.getId(), a.getId()));

        return allDeviceActions;
    }

}
