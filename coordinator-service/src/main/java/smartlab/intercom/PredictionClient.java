package smartlab.intercom;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import smartlab.model.PredictionPackage;
import smartlab.model.UserTemperatureProfile;
import smartlab.model.Vote;

import java.util.List;

@FeignClient("prediction-service")
public interface PredictionClient {

    @PostMapping("/predict")
    UserTemperatureProfile predictTemperature(@RequestBody PredictionPackage predictionPackage);
}
