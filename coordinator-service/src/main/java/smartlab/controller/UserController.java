package smartlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smartlab.model.Configuracao;
import smartlab.model.Recomendacao;
import smartlab.model.RecomendacaoPackage;
import smartlab.model.UserPreference;
import smartlab.repository.ConfiguracaoRepository;
import smartlab.repository.RecomendacaoPackageRepository;
import smartlab.repository.VoteRepository;
import smartlab.service.TemperatureService;
import smartlab.service.UserVoteService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserVoteService userVoteService;

    @Autowired
    private RecomendacaoPackageRepository recomendacaoPackageRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private TemperatureService temperatureService;

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

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
        return recomendacaoPackageRepository.findTopByOrderByIdDesc()
                .getTemperaturaUtilizada();
    }

    @GetMapping("/consensusWithProfiles")
    public RecomendacaoPackage getConsensusWithProfile(){
        RecomendacaoPackage recomendacaoPackage =  recomendacaoPackageRepository
                .findTopByOrderByIdDesc();

        recomendacaoPackage.getUserTemperatureProfiles();

        return recomendacaoPackage;
    }

    @GetMapping("/preferencias")
    public List<UserPreference> preferencias(){
        return voteRepository.findAll();
    }


    @PostMapping("/setConfig")
    public void setConfiguracao(@RequestBody Configuracao configuracao){
        configuracaoRepository.save(configuracao);
    }
}
