package com.example.iotproject.dao;
import com.example.iotproject.model.DeviceAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDAO extends JpaRepository<DeviceAction, Long>{
    
}
