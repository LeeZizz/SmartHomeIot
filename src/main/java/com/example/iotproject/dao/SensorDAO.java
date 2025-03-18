package com.example.iotproject.dao;

import com.example.iotproject.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface SensorDAO extends JpaRepository<SensorData, Long> {
    SensorData findTopByOrderByTimestampDesc();
    List<SensorData> findAll();
    // Tìm kiếm dữ liệu theo các tiêu chí nhiệt độ, độ ẩm và độ sáng
    @Query("SELECT s FROM SensorData s WHERE " +
            "(COALESCE(:temperature, s.temperature) = s.temperature) AND " +
            "(COALESCE(:humidity, s.humidity) = s.humidity) AND " +
            "(COALESCE(:brightness, s.brightness) = s.brightness)")
    List<SensorData> findByCriteria(@Param("temperature") Double temperature,
                                    @Param("humidity") Double humidity,
                                    @Param("brightness") Double brightness);
}
