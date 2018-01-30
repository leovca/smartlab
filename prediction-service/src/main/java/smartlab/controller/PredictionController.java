package smartlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smartlab.model.PredictionPackage;
import smartlab.model.UserTemperatureProfile;
import smartlab.service.KNNService;

@RestController
public class PredictionController {

    @Autowired
    KNNService knnService;

    @PostMapping("/predict")
    public UserTemperatureProfile predictTemperature(@RequestBody PredictionPackage dados) throws Exception {
        return knnService.calculateTemperatureProfile(dados);
    }

}
