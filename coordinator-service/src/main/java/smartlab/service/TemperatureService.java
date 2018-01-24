package smartlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartlab.intercom.ConsensusClient;
import smartlab.intercom.EdgeClient;
import smartlab.intercom.PredictionClient;
import smartlab.model.*;
import smartlab.repository.VoteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemperatureService {

    @Autowired
    EdgeClient edgeClient;

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

        vote.setOnlineUsers(edgeClient.onlineUsers().size());
        vote.setExternalTemperature(edgeClient.externalTemperature());
        vote.setInternalTemperature(edgeClient.internalTemperature());

        vote.setVote(16);

        return vote;
    }

    public void updateTemperature(){
        List<Integer> onlineUsers = edgeClient.onlineUsers();
        UserPreference current = getCurrent();

        List<UserTemperatureProfile> profiles = onlineUsers.stream()
                .map(voteRepository::queryTemperatureProfile)
                .filter(votes -> !votes.isEmpty())
                .map(votes -> new PredictionPackage(votes, current))
                .map(predictionClient::predictTemperature)
                .collect(Collectors.toList());

        if(profiles.isEmpty()){
            edgeClient.shutdownAir();
        } else {
            Recomendacao finalTemperature = consensusClient.getNewTemperature(profiles, AlgorithmsType.AverageWithoutMisery);
            edgeClient.setAirTemperature(finalTemperature.getConsenso().getRotulo());
        }

    }
}
