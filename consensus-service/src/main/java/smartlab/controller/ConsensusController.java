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

    @PostMapping("calculateTemperature/{idAlgorithm}")
    public Integer getNewTemperature(@RequestBody List<UserTemperatureProfile> userTemperatureProfiles,
                              @PathVariable("idAlgorithm") Integer idAlgorithm){

        //coloca aqui suas pohhas

        return 25;
    }

}
