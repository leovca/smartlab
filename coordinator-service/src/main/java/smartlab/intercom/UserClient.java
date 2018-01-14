package smartlab.intercom;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import smartlab.model.User;
import smartlab.model.UserDataToPredict;

import java.util.List;

@FeignClient("database-service")
public interface UserClient {

    @RequestMapping("/users/on")
    List<User> findOnlineUsers();

    @RequestMapping("/users/1/to_predict")
    List<UserDataToPredict> getUserDetails();
}
