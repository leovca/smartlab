package smartlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartlab.intercom.EdgeClient;
import smartlab.model.Vote;
import smartlab.repository.VoteRepository;

@Service
public class UserVoteService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    EdgeClient edgeClient;


    public void registerTemperatureVote(Integer userId, Integer userVote){

        Vote vote = new Vote();
        vote.setUserId(userId);
        vote.setVote(userVote);
        vote.setExternalTemperature(edgeClient.externalTemperature());
        vote.setInternalTemperature(edgeClient.internalTemperature());
        vote.setOnlineUsers(edgeClient.onlineUsers());

        voteRepository.save(vote);

    }

    public void getUserTemperatureProfile(Integer userId){

    }

}
