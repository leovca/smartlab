package smartlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smartlab.intercom.CoordiantorUserClient;
import smartlab.intercom.SensorClient;
import smartlab.model.Recomendacao;
import smartlab.model.RecomendacaoPackage;
import smartlab.model.SensorData;
import smartlab.model.UserPreference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class SensorController {

    @Autowired
    SensorClient sensorClient;

    @Autowired
    CoordiantorUserClient coordiantorUserClient;

    @GetMapping("/internalTemperature")
    public Double internalTemperature(){
        return sensorClient.internalTemperature();
    }

    @GetMapping("/externalTemperature")
    public Double externalTemperature(){
        return sensorClient.externalTemperature();
    }

    @GetMapping("/sensor/{id}")
    public SensorData getActualSensorValue(@PathVariable("id") String id){
        return sensorClient.getActualSensorValue(id);
    }

    @GetMapping("/sensor/{id}/values")
    public List<SensorData> getAllSensorValue(String id){
        return sensorClient.getAllSensorValue(id);
    }
    @GetMapping("/sensor/{id}/values/date")
    public List<SensorData> getAllSensorValue(@RequestParam("startDate")  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dataInicial,
                                              @RequestParam("endDate")  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dataFinal,
                                              @PathVariable("id") String id){

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sensorClient.getAllSensorValue(format.format(dataInicial), format.format(dataFinal), id);
    }

    @GetMapping("/temperature/consensus")
    public Double getConsensus(){
        return coordiantorUserClient.getConsensus();
    }

    @GetMapping("/temperature/votes")
    public List<UserPreference> getAllVotes(){
        return coordiantorUserClient.preferencias();
    }

    @GetMapping("/temperature/consensusWithProfiles")
    RecomendacaoPackage getConsensusWithProfile(){
        return coordiantorUserClient.getConsensusWithProfile();
    }

}
