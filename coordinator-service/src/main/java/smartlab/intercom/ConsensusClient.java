package smartlab.intercom;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import smartlab.model.UserTemperatureProfile;
import smartlab.model.Vote;

import java.util.List;

@FeignClient("consensus-service")
public interface ConsensusClient {

    @PostMapping("calculateTemperature/{idAlgorithm}")
    Integer getNewTemperature(@RequestBody List<UserTemperatureProfile> userTemperatureProfiles,
                                     @PathVariable("idAlgorithm") Integer idAlgorithm);
}
