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
                              @PathVariable("idAlgorithm") Integer idAlgorithm){

        //coloca aqui suas pohhas
        AlgorithmsFactory factory = new AlgorithmsFactory();
        Recomendacao recomendacao = new Recomendacao();


        switch (idAlgorithm) {
            case 1:
                AverageWithoutMisery averageWith = (AverageWithoutMisery) factory.getAlgorithm(AlgorithmsType.AverageWithoutMisery);
                Vote voteaverageWith = averageWith.GetResult(listUser, 1);

                recomendacao.setNameAlgorithms("AverageWithoutMisery");
                recomendacao.setConsenso(voteaverageWith);
                recomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));


                return recomendacao;

            case 2:
                BordaCount borderCount = (BordaCount) factory.getAlgorithm(AlgorithmsType.BorderCount);
                Vote voteborderCount = borderCount.GetResult(listUser);

                recomendacao.setNameAlgorithms("BorderCount");
                recomendacao.setConsenso(voteborderCount);
                recomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));


                return recomendacao;
            case 3:
                LeastMisery leastMisery = (LeastMisery) factory.getAlgorithm(AlgorithmsType.LeastMisery);
                Vote voteleastMisery = leastMisery.GetResult(listUser);

                recomendacao.setNameAlgorithms("LeastMisery");
                recomendacao.setConsenso(voteleastMisery);
                recomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));


                return recomendacao;


            case 4:
                MostPleasure mostPleasure = (MostPleasure) factory.getAlgorithm(AlgorithmsType.MostPleasure);
                Vote voteMostPleasure = mostPleasure.GetResult(listUser);

                recomendacao.setNameAlgorithms("MostPleasure");
                recomendacao.setConsenso(voteMostPleasure);
                recomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));


                return recomendacao;

            case 5:
                Multiplicative multiplicative = (Multiplicative) factory.getAlgorithm(AlgorithmsType.Multiplicative);
                Vote voteMultiplicative = multiplicative.GetResult(listUser);

                recomendacao.setNameAlgorithms("Multiplicative");
                recomendacao.setConsenso(voteMultiplicative);
                recomendacao.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));


                return recomendacao;

        }

        recomendacao.setNameAlgorithms("Error parâmetro inválido");
        return  recomendacao;
    }

}
