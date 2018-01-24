package smartlab.intercom;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import smartlab.model.AlgorithmsType;
import smartlab.model.Recomendacao;
import smartlab.model.UserTemperatureProfile;

import java.util.List;

@FeignClient("consensus-service")
public interface ConsensusClient {

    @PostMapping("calculateTemperature/{algorithmsType}")
    Recomendacao getNewTemperature(@RequestBody List<UserTemperatureProfile> listUser,
                                          @PathVariable("algorithmsType") AlgorithmsType algorithmsType);
}