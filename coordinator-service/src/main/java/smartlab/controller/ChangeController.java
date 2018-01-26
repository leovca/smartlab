package smartlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import smartlab.service.TemperatureService;

@RestController
public class ChangeController {

    @Autowired
    TemperatureService temperatureService;

    @GetMapping("/changeDetected")
    public void changeDetected(){
        temperatureService.updateTemperature();
    }
}
