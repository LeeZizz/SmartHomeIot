package com.example.iotproject.controller;

import com.example.iotproject.dto.SensorDTO;
import com.example.iotproject.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @PostMapping("/data")
    public String receiveSensorData(@RequestBody SensorDTO sensorDataDTO) {
        sensorService.saveSensorData(sensorDataDTO);
        return "Data saved successfully!";
    }

    // API để lấy dữ liệu mới nhất từ cảm biến
    @GetMapping("/sensor-data")
    public SensorDTO getSensorData() {
        return sensorService.getLatestSensorData();
    }

    // API để lấy tất cả dữ liệu cảm biến
    @GetMapping("/all")
    public ResponseEntity<List<SensorDTO>> getAllSensorData() {
        List<SensorDTO> sensorDataList = sensorService.getAllSensorData();
        // Sắp xếp theo ID giảm dần (ID lớn hơn là mới nhất)
        sensorDataList.sort((a, b) -> Long.compare(b.getId(), a.getId()));
        return ResponseEntity.ok(sensorDataList);
    }
    // API phân trang với sắp xếp
    @GetMapping("/row")
    public Page<SensorDTO> getSensors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "7") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        PageRequest pageRequest = PageRequest.of(page, pageSize,
                "desc".equalsIgnoreCase(sortOrder) ?
                        Sort.by(Sort.Order.desc(sortBy)) :
                        Sort.by(Sort.Order.asc(sortBy)));

        return sensorService.getAllSensors(pageRequest);
    }

    // API lấy dữ liệu Sensor sắp xếp
    @GetMapping("/sorted")
    public List<SensorDTO> getAllSensorsSorted(
            @RequestParam(defaultValue = "id") String sortBy, // Trường sắp xếp, mặc định là id
            @RequestParam(defaultValue = "asc") String order // Thứ tự sắp xếp, mặc định là tăng dần (asc)
    ) {
        return sensorService.getAllSensorsSorted(sortBy, order);
    }
    // API tìm kiếm theo nhiệt độ, độ ẩm và độ sáng
    @GetMapping("/search")
    public ResponseEntity<List<SensorDTO>> searchSensors(
            @RequestParam(value = "temperature", required = false) Double temperature,
            @RequestParam(value = "humidity", required = false) Double humidity,
            @RequestParam(value = "brightness", required = false) Double brightness) {
        try {
            List<SensorDTO> sensors = sensorService.searchSensors(temperature, humidity, brightness);
            return ResponseEntity.ok(sensors);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}

