package com.example.iotproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class DeviceDTO {
    private Long id;
    private String device;
    private String action;

    // Sử dụng @JsonFormat để chỉ định định dạng ngày giờ
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;

    // Constructor mặc định (required by Jackson)
    public DeviceDTO() {}

    // Constructor có tham số (dùng cho các trường hợp khác)
    public DeviceDTO(String device, String action, LocalDateTime timestamp) {
        this.device = device;
        this.action = action;
        this.timestamp = timestamp;
    }

    // Constructor có ID, có thể sử dụng cho cơ sở dữ liệu
    public DeviceDTO(Long id, String device, String action, LocalDateTime timestamp) {
        this.id = id;
        this.device = device;
        this.action = action;
        this.timestamp = timestamp;
    }

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}