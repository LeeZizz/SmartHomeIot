package com.example.iotproject.service;


import com.example.iotproject.dao.SensorDAO;
import com.example.iotproject.dto.SensorDTO;
import com.example.iotproject.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;


@Service
public class SensorService {
    @Autowired
    private SensorDAO sensorDAO;

    public SensorData saveSensorData(SensorDTO sensorDataDTO) {
        SensorData sensorData = new SensorData();
        sensorData.setTemperature(sensorDataDTO.getTemperature());
        sensorData.setHumidity(sensorDataDTO.getHumidity());
        sensorData.setBrightness(sensorDataDTO.getBrightness());
        sensorData.setTimestamp(LocalDateTime.now());
        return sensorDAO.save(sensorData);
    }


    // Phương thức lấy dữ liệu mới nhất từ cơ sở dữ liệu
    public SensorDTO getLatestSensorData() {
        // Giả sử lấy dữ liệu sensor mới nhất từ cơ sở dữ liệu
        SensorData latestData = sensorDAO.findTopByOrderByTimestampDesc(); // Query lấy bản ghi mới nhất

        if (latestData != null) {
            return new SensorDTO(
                    latestData.getId(),
                    latestData.getTemperature(),
                    latestData.getHumidity(),
                    latestData.getBrightness(),
                    latestData.getDust(),
                    latestData.getTimestamp()
            );
        } else {
            // Trường hợp không có dữ liệu
            return new SensorDTO(null, 0, 0, 0,0, LocalDateTime.now()); // Giá trị mặc định
        }
    }
    // Phương thức lấy tất cả dữ liệu từ cơ sở dữ liệu
    public List<SensorDTO> getAllSensorData() {
        List<SensorData> sensorDataList = sensorDAO.findAll();
        return sensorDataList.stream()
                .map(sensorData -> new SensorDTO(
                        sensorData.getId(),
                        sensorData.getTemperature(),
                        sensorData.getHumidity(),
                        sensorData.getBrightness(),
                        sensorData.getDust(),
                        sensorData.getTimestamp()
                ))
                .collect(Collectors.toList());
    }
    // Phương thức lấy dữ liệu phân trang
    public Page<SensorDTO> getAllSensors(PageRequest pageRequest) {
        return sensorDAO.findAll(pageRequest).map(sensor -> new SensorDTO(sensor));
    }


    public List<SensorDTO> getAllSensorsSorted(String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        List<SensorData> sortedSensors = sensorDAO.findAll(sort);

        return sortedSensors.stream()
                .map(sensorData -> new SensorDTO(
                        sensorData.getId(),
                        sensorData.getTemperature(),
                        sensorData.getHumidity(),
                        sensorData.getBrightness(),
                        sensorData.getDust(),
                        sensorData.getTimestamp()
                ))
                .collect(Collectors.toList());
    }
//    public List<SensorDTO> searchSensors(Double temperature, Double humidity, Double brightness) {
//        try {
//            if (temperature == null) temperature = Double.NaN;
//            if (humidity == null) humidity = Double.NaN;
//            if (brightness == null) brightness = Double.NaN;
//
//            List<SensorData> sensorDataList = sensorDAO.findByCriteria(temperature, humidity, brightness);
//            return sensorDataList.stream()
//                    .map(sensorData -> new SensorDTO(
//                            sensorData.getId(),
//                            sensorData.getTemperature(),
//                            sensorData.getHumidity(),
//                            sensorData.getBrightness(),
//                            sensorData.getTimestamp()))
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            e.printStackTrace();  // Log chi tiết lỗi
//            throw new RuntimeException("Error while searching sensors", e);
//        }
//    }

    // Phương thức tìm kiếm theo nhiệt độ, độ ẩm và độ sáng
    public List<SensorDTO> searchSensors(Double temperature, Double humidity, Double brightness) {
        List<SensorData> sensorDataList = sensorDAO.findByCriteria(temperature, humidity, brightness);
        return sensorDataList.stream()
                .map(sensorData -> new SensorDTO(
                        sensorData.getId(),
                        sensorData.getTemperature(),
                        sensorData.getHumidity(),
                        sensorData.getBrightness(),
                        sensorData.getDust(),
                        sensorData.getTimestamp()
                ))
                .collect(Collectors.toList());
    }


}
