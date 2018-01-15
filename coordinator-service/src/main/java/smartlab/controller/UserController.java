package smartlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smartlab.service.TemperatureService;
import smartlab.service.UserVoteService;

@RestController
public class UserController {

    @Autowired
    private UserVoteService userVoteService;

    @Autowired
    private TemperatureService temperatureService;

    @PostMapping("user/{userId}/temperature")
    public Boolean receiverTemperatureVote(@PathVariable("userId") Integer userId, @RequestBody Integer userVote){
        userVoteService.registerTemperatureVote(userId, userVote);
        temperatureService.updateTemperature();
        return Boolean.TRUE;
    }
}
