package smartlab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorController {

    @GetMapping("/internalTemperature")
    public Float internalTemperature(){
        return 23f;
    }

    @GetMapping("/externalTemperature")
    public Float externalTemperature(){
        return 28f;
    }

    @GetMapping("/onlineUsers")
    public Integer onlineUsers(){
        return 5;
    }

}
