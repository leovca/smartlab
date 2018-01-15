package smartlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartlab.intercom.ConsensusClient;
import smartlab.intercom.EdgeClient;
import smartlab.intercom.PredictionClient;
import smartlab.intercom.UserClient;
import smartlab.model.PredictionPackage;
import smartlab.model.UserTemperatureProfile;
import smartlab.model.Vote;
import smartlab.repository.VoteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemperatureService {

    @Autowired
    EdgeClient edgeClient;

    @Autowired
    UserClient userClient;

    @Autowired
    PredictionClient predictionClient;

    @Autowired
    ConsensusClient consensusClient;

    @Autowired
    UserVoteService userVoteService;

    @Autowired
    VoteRepository voteRepository;

    private Vote getCurrent(){
        Vote vote = new Vote();

        vote.setOnlineUsers(edgeClient.onlineUsers());
        vote.setExternalTemperature(edgeClient.externalTemperature());
        vote.setInternalTemperature(edgeClient.internalTemperature());
        vote.setOnlineUsers(edgeClient.onlineUsers());

        vote.setVote(0);

        return vote;
    }

    public void updateTemperature(){
        List<Integer> onlineUsers = Arrays.asList(userClient.getOnlineUsers());
        Vote current = getCurrent();

        List<UserTemperatureProfile> profiles = onlineUsers.stream()
                .map(voteRepository::queryTemperatureProfile)
                .filter(votes -> !votes.isEmpty())
                .map(votes -> new PredictionPackage(votes, current))
                .map(predictionClient::predictTemperature)
                .collect(Collectors.toList());

        Integer finalTemperature = consensusClient.getNewTemperature(profiles, 1);

        edgeClient.setAirTemperature(finalTemperature);
    }
}
