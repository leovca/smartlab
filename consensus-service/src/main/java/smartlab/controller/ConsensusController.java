package smartlab.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smartlab.algoritmos.*;
import smartlab.model.Recomendacao;
import smartlab.model.User;
import smartlab.model.Vote;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsensusController {

    @PostMapping("calculateTemperature/{algorithmsType}")
    public Recomendacao getNewTemperature(@RequestBody List<User> listUser,
                              @PathVariable("algorithmsType") AlgorithmsType algorithmsType){

        AlgorithmsFactory factory = new AlgorithmsFactory();
        Recomendacao recomendacao = new Recomendacao();

        Algorithms algoritimo = factory.getAlgorithm(algorithmsType);
        Vote vote;
        recomendacao.setNameAlgorithms(algorithmsType.name());
        if(algorithmsType==AlgorithmsType.AverageWithoutMisery){
            vote = algoritimo.GetResult(listUser, 1);
        } else {
            vote = algoritimo.GetResult(listUser);
        }
        recomendacao.setConsenso(vote);
        recomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));

        return recomendacao;
    }


    @PostMapping("calculateTemperatura/recommenderAll")
    public List<Recomendacao> getAllRecommendation(@RequestBody List<User> listUser){

        AlgorithmsFactory factory = new AlgorithmsFactory();
        List<Recomendacao> listRecomendacao = new ArrayList<>();

        Algorithms averageWith = factory.getAlgorithm(AlgorithmsType.AverageWithoutMisery);
        Vote voteaverageWith = averageWith.GetResult(listUser, 1);

        Recomendacao avarageRecomendacao = new Recomendacao();

        avarageRecomendacao.setNameAlgorithms(AlgorithmsType.AverageWithoutMisery.name());
        avarageRecomendacao.setConsenso(voteaverageWith);
        avarageRecomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));

        listRecomendacao.add(avarageRecomendacao);

        Algorithms borderCount = factory.getAlgorithm(AlgorithmsType.BorderCount);
        Vote voteborderCount = borderCount.GetResult(listUser);

        Recomendacao borderRecomendacao = new Recomendacao();

        borderRecomendacao.setNameAlgorithms(AlgorithmsType.BorderCount.name());
        borderRecomendacao.setConsenso(voteborderCount);
        borderRecomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));

        listRecomendacao.add(borderRecomendacao);

        Algorithms leastMisery = factory.getAlgorithm(AlgorithmsType.LeastMisery);
        Vote voteleastMisery = leastMisery.GetResult(listUser);

        Recomendacao leastMiseryRecomendacao = new Recomendacao();

        leastMiseryRecomendacao.setNameAlgorithms(AlgorithmsType.LeastMisery.name());
        leastMiseryRecomendacao.setConsenso(voteleastMisery);
        leastMiseryRecomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));

        listRecomendacao.add(leastMiseryRecomendacao);

        Algorithms mostPleasure = factory.getAlgorithm(AlgorithmsType.MostPleasure);
        Vote voteMostPleasure = mostPleasure.GetResult(listUser);

        Recomendacao mostPleasureRecomendacao = new Recomendacao();

        mostPleasureRecomendacao.setNameAlgorithms(AlgorithmsType.MostPleasure.name());
        mostPleasureRecomendacao.setConsenso(voteMostPleasure);
        mostPleasureRecomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));

        listRecomendacao.add(mostPleasureRecomendacao);

        Algorithms multiplicative = factory.getAlgorithm(AlgorithmsType.Multiplicative);
        Vote voteMultiplicative = multiplicative.GetResult(listUser);

        Recomendacao multiplicativeRecomendacao = new Recomendacao();

        multiplicativeRecomendacao.setNameAlgorithms(AlgorithmsType.Multiplicative.name());
        multiplicativeRecomendacao.setConsenso(voteMultiplicative);
        multiplicativeRecomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));

        listRecomendacao.add(multiplicativeRecomendacao);

        return listRecomendacao;

    }


}
