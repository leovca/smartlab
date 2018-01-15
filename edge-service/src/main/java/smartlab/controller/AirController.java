package smartlab.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AirController {

    Logger logger = Logger.getLogger(AirController.class.getName());


    @PostMapping("air/temperature")
    public void setAirTemperature(@RequestBody Integer temperature){
        logger.info("Temperature: " + temperature);
    }

}
