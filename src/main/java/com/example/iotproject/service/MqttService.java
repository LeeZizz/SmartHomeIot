package com.example.iotproject.service;

import com.example.iotproject.dao.SensorDAO;
import com.example.iotproject.model.SensorData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Service
public class MqttService {

    @Autowired
    private SensorDAO sensorDataRepository;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMqttMessage(Message<?> message) {
        String jsonMessage = (String) message.getPayload();
        // Chuyển đổi chuỗi JSON thành đối tượng SensorData
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SensorData sensorData = objectMapper.readValue(jsonMessage, SensorData.class);
            sensorData.setTimestamp(LocalDateTime.now());  // Thêm thời gian
            sensorDataRepository.save(sensorData);
            System.out.println("Dữ liệu đã được lưu vào cơ sở dữ liệu: " + sensorData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

