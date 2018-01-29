package smartlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import smartlab.model.SensorData;
import smartlab.model.SensorType;
import smartlab.model.Unit;
import smartlab.repository.SensorDataRepository;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class SensorController {

    @Autowired
    SensorDataRepository sensorDataRepository;

    @GetMapping("/internalTemperature")
    public Double internalTemperature(){
        SensorData sensor = sensorDataRepository.findTopByIdSensorOrderByTimeDesc("tmp1");
        return Double.valueOf(sensor.getValue());
    }

    @GetMapping("/externalTemperature")
    public Double externalTemperature(){
        SensorData sensor = sensorDataRepository.findTopByIdSensorOrderByTimeDesc("tmp2");
        return Double.valueOf(sensor.getValue());
    }

    @GetMapping("/sensor/{id}")
    public SensorData getActualSensorValue(@PathVariable("id") String id){
        return sensorDataRepository.findTopByIdSensorOrderByTimeDesc(id);
    }

    @GetMapping("/sensor/{id}/values")
    public List<SensorData> getAllSensorValue(String id){
        return sensorDataRepository.findByIdSensorOrderByTimeDesc(id);
    }
    @GetMapping("/sensor/{id}/values/date")
    public List<SensorData> getAllSensorValue(@RequestParam("startDate")  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dataInicial,
                                              @RequestParam("endDate")  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dataFinal,
                                              @PathVariable("id") String id){
        return sensorDataRepository.findByIdSensorAndTimeBetween(id, dataInicial, dataFinal);
    }

    @PostMapping("externalTemperature")
    public void setExternalTemperature(@RequestBody Double temperature){
        SensorData sensorData = new SensorData();
        sensorData.setIdSensor("tmp2");
        sensorData.setTime(Calendar.getInstance().getTime());
        sensorData.setUnit(Unit.CELCIUS);
        sensorData.setValue(String.valueOf(temperature));
        sensorData.setSensorType(SensorType.TEMPERATURE);

        sensorDataRepository.save(sensorData);
    }

    @PostMapping("internalTemperature")
    public void setInternalTemperature(@RequestBody Double temperature){
        SensorData sensorData = new SensorData();
        sensorData.setIdSensor("tmp1");
        sensorData.setTime(Calendar.getInstance().getTime());
        sensorData.setUnit(Unit.CELCIUS);
        sensorData.setValue(String.valueOf(temperature));
        sensorData.setSensorType(SensorType.TEMPERATURE);

        sensorDataRepository.save(sensorData);
    }

}
