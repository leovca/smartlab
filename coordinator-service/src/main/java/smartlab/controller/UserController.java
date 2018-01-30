package smartlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smartlab.model.Recomendacao;
import smartlab.model.UserPreference;
import smartlab.repository.RecomendacaoRepository;
import smartlab.repository.VoteRepository;
import smartlab.service.TemperatureService;
import smartlab.service.UserVoteService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserVoteService userVoteService;

    @Autowired
    private RecomendacaoRepository recomendacaoRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private TemperatureService temperatureService;

    @PostMapping("user/{userId}/temperature")
    public Boolean receiverTemperatureVote(@PathVariable("userId") Integer userId, @RequestBody Integer userVote){
        if(userVote< 16 || userVote>30){
            throw new RuntimeException("Temperatura fora do intervalo");
        }
        userVoteService.registerTemperatureVote(userId, userVote);
        temperatureService.updateTemperature();
        return Boolean.TRUE;
    }


    @GetMapping("/consensus")
    public Double getConsensus(){
        return recomendacaoRepository.findTopByOrderByIdDesc().getConsenso();
    }

    @GetMapping("/consensusWithProfiles")
    public Recomendacao getConsensusWithProfile(){
        return recomendacaoRepository.findTopByOrderByIdDesc();
    }

    @GetMapping("/preferencias")
    public List<UserPreference> preferencias(){
        return voteRepository.findAll();
    }
}
