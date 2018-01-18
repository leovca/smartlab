package smartlab.controller;

import org.springframework.web.bind.annotation.*;
import smartlab.model.Temperature;
import smartlab.model.UserTemperatureProfile;

import java.util.List;

@RestController
public class ConsensusController {

    @PostMapping("calculateTemperature/{idAlgortimo}")
    public Integer getNewTemperature(@RequestBody List<UserTemperatureProfile> userTemperatureProfiles,
                                     @PathVariable("idAlgoritimo") Integer algoritimo) {

        //coloca aqui suas pohhas

        return 25;
    }

    @GetMapping("Temperature")
    public Integer getTemperature() {

        //coloca aqui suas pohhas

        return 25;
    }

}
