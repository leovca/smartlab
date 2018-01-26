package smartlab.intercom;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("coordinator-service")
public interface CoordiantorUserClient {

    @GetMapping("changeDetected")
    void changeDetected();

    @GetMapping("/consensus")
    Double getConsensus();
}
