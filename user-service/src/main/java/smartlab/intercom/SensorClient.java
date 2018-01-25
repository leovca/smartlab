package smartlab.intercom;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import smartlab.model.SensorData;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@FeignClient("edge-service")
public interface SensorClient {

    @GetMapping("/internalTemperature")
    Double internalTemperature();

    @GetMapping("/externalTemperature")
    Double externalTemperature();

    @GetMapping("/sensor/{id}")
    SensorData getActualSensorValue(@PathVariable("id") String id);

    @GetMapping("/sensor/{id}/values")
    List<SensorData> getAllSensorValue(String id);

    @GetMapping("/sensor/{id}/values/date")
    List<SensorData> getAllSensorValue(@RequestParam("startDate") String dataInicial,
                                              @RequestParam("endDate")  String dataFinal,
                                              @PathVariable("id") String id);
}
