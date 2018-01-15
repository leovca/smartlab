package smartlab.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smartlab.model.Temperature;
import smartlab.model.UserTemperatureProfile;

import java.util.List;

@RestController
public class ConsensusController {

    @PostMapping("calculateTemperature/{idAlgortimo}")
    public Integer getNewTemperature(@RequestBody List<UserTemperatureProfile> userTemperatureProfiles,
                                     @PathVariable("idAlgoritimo") Integer algoritimo){

        //coloca aqui suas pohhas

        return 25;
    }

}
