package smartlab.intercom;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("user/online")
    Integer[] getOnlineUsers();
}
