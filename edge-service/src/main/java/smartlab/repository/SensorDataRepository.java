package smartlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smartlab.model.SensorData;
import smartlab.model.UserPresence;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {

//    @Query("select sd from SensorData sd where sd.idSensor = ?1 order by sd.time desc limit 1")
//    SensorData getActualSensorValue(String id);

    SensorData findTopByIdSensorOrderByTimeDesc(String sensor);
    List<SensorData> findByIdSensorOrderByTimeDesc(String sensor);
//
//    @Query("select sd from SensorData sd where sd.idSensor = ?1 order by sd.time desc")
//    List<SensorData> getAllSensorValue(String id);
    List<SensorData> findByIdSensorAndTimeBetween(String id, Date data1, Date data2);
//    @Query(value = "SELECT * FROM topiv.sensor_data where idsensor = ?1 and (time >= ?2 and time <= ?3)", nativeQuery = true)
//    List<SensorData> getSensorValuesByDate(String id, String startTime, String endTime);
}
