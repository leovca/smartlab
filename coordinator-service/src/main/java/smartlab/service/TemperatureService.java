package smartlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartlab.intercom.EdgeClient;
import smartlab.intercom.UserClient;
import smartlab.model.Vote;
import smartlab.repository.VoteRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class TemperatureService {

    @Autowired
    EdgeClient edgeClient;

    @Autowired
    UserClient userClient;

    @Autowired
    UserVoteService userVoteService;

    @Autowired
    VoteRepository voteRepository;

    public void updateTemperature(){
        List<Integer> onlineUsers = Arrays.asList(userClient.getOnlineUsers());


        List<Vote> votes = voteRepository.queryTemperatureProfile(1231);

        edgeClient.setAirTemperature(25);
    }
}
