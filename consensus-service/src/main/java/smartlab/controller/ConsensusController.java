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
import java.util.List;

@RestController
public class ConsensusController {

    @PostMapping("calculateTemperature/{idAlgorithm}")
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

}
