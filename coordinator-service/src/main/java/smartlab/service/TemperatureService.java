package smartlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartlab.intercom.ConsensusClient;
import smartlab.intercom.EdgeClient;
import smartlab.intercom.PredictionClient;
import smartlab.intercom.UserClient;
import smartlab.model.*;
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

    private UserPreference getCurrent(){
        UserPreference vote = new UserPreference();

        vote.setOnlineUsers(edgeClient.onlineUsers());
        vote.setExternalTemperature(edgeClient.externalTemperature());
        vote.setInternalTemperature(edgeClient.internalTemperature());
        vote.setOnlineUsers(edgeClient.onlineUsers());

        vote.setVote(16);

        return vote;
    }

    public void updateTemperature(){
        List<Integer> onlineUsers = Arrays.asList(userClient.getOnlineUsers());
        UserPreference current = getCurrent();

        List<UserTemperatureProfile> profiles = onlineUsers.stream()
                .map(voteRepository::queryTemperatureProfile)
                .filter(votes -> !votes.isEmpty())
                .map(votes -> new PredictionPackage(votes, current))
                .map(predictionClient::predictTemperature)
                .collect(Collectors.toList());

       Recomendacao finalTemperature = consensusClient.getNewTemperature(profiles, AlgorithmsType.AverageWithoutMisery);

        edgeClient.setAirTemperature(finalTemperature.getConsenso().getRotulo());
    }
}
