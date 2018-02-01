package smartlab.intercom;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import smartlab.model.Recomendacao;
import smartlab.model.RecomendacaoPackage;
import smartlab.model.UserPreference;

import java.util.List;

@FeignClient("coordinator-service")
public interface CoordiantorUserClient {

    @PostMapping("user/{userId}/temperature")
    Boolean receiverTemperatureVote(@PathVariable("userId") Integer userId, @RequestBody Integer userVote);

    @GetMapping("/consensus")
    Double getConsensus();

    @GetMapping("/preferencias")
    List<UserPreference> preferencias();

    @GetMapping("/consensusWithProfiles")
    RecomendacaoPackage getConsensusWithProfile();
}
