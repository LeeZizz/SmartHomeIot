package com.example.iotproject.dto;

import com.example.iotproject.model.SensorData;

import java.time.LocalDateTime;

public class SensorDTO {
    private Long id;
    private float temperature;
    private float humidity;
    private float brightness;
    private float dust;
    private LocalDateTime timestamp;
    // Constructor để ánh xạ từ Sensor entity sang SensorDTO
    public SensorDTO(SensorData sensor) {
        this.id = sensor.getId();
        this.temperature = sensor.getTemperature();
        this.humidity = sensor.getHumidity();
        this.brightness = sensor.getBrightness();
        this.dust = sensor.getDust();
        this.timestamp = sensor.getTimestamp();
    }
    // Constructor
    public SensorDTO(Long id, float temperature, float humidity, float brightness,float dust, LocalDateTime timestamp) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.brightness = brightness;
        this.dust = dust;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public float getDust() {
        return dust;
    }

    public void setDust(float dust) {
        this.dust = dust;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
