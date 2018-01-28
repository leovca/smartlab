package smartlab.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smartlab.algoritmos.*;
import smartlab.model.Recomendacao;
import smartlab.model.User;


import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsensusController {

    @PostMapping("calculateTemperature/{algorithmsType}")
    public Recomendacao getNewTemperature(@RequestBody List<User> listUser,
                              @PathVariable("algorithmsType") AlgorithmsType algorithmsType){
        Algorithms algoritmo = AlgorithmsFactory.getAlgorithm(algorithmsType);
        return algoritmo.getRecomendacao(listUser);
    }


      @PostMapping("calculateTemperature/recommenderAll")
      public List<Recomendacao> getAllRecommendation(@RequestBody List<User> listUser){


          List<Recomendacao> listRecomendacao = new ArrayList<>();

          Algorithms averageWith = AlgorithmsFactory.getAlgorithm(AlgorithmsType.AverageWithoutMisery);
          listRecomendacao.add(averageWith.getRecomendacao(listUser));

          Algorithms leastMisery = AlgorithmsFactory.getAlgorithm(AlgorithmsType.LeastMisery);
          listRecomendacao.add( leastMisery.getRecomendacao(listUser));

          Algorithms mostPleasure = AlgorithmsFactory.getAlgorithm(AlgorithmsType.MostPleasure);
          listRecomendacao.add(mostPleasure.getRecomendacao(listUser));

          Algorithms multiplicative = AlgorithmsFactory.getAlgorithm(AlgorithmsType.Multiplicative);
          listRecomendacao.add(multiplicative.getRecomendacao(listUser));

          Algorithms borderCount = AlgorithmsFactory.getAlgorithm((AlgorithmsType.BorderCount));
          listRecomendacao.add(borderCount.getRecomendacao(listUser));

         return listRecomendacao;

    }


}
