package smartlab.intercom;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("edge-service")
public interface EdgeClient {

    @GetMapping("/internalTemperature")
    Float internalTemperature();

    @GetMapping("/externalTemperature")
    Float externalTemperature();

    @GetMapping("/onlineUsers")
    Integer onlineUsers();

    @PostMapping("air/temperature")
    void setAirTemperature(@RequestBody Double temperature);
}
