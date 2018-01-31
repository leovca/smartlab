package smartlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartlab.intercom.ConsensusClient;
import smartlab.intercom.EdgeClient;
import smartlab.intercom.PredictionClient;
import smartlab.model.*;
import smartlab.repository.ConfiguracaoRepository;
import smartlab.repository.RecomendacaoPackageRepository;
import smartlab.repository.VoteRepository;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
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

    @Autowired
    RecomendacaoPackageRepository recomendacaoPackageRepository;

    @Autowired
    ConfiguracaoRepository configuracaoRepository;

    private UserPreference getCurrent(){
        UserPreference vote = new UserPreference();

        vote.setOnlineUsers(edgeClient.onlineUsers().size());
        vote.setExternalTemperature(edgeClient.externalTemperature());
        vote.setInternalTemperature(edgeClient.internalTemperature());

        vote.setVote(16);

        return vote;
    }

    public void updateTemperature(){

        Configuracao configuracao = configuracaoRepository.findTopByOrderByIdDesc();
        if(configuracao == null){
            configuracao = new Configuracao();
        }
        if(configuracao.getAlgorithmsType() == null){

            configuracao.setAlgorithmsType(AlgorithmsType.AverageWithoutMisery);
        }

        List<Integer> onlineUsers = edgeClient.onlineUsers();
        UserPreference current = getCurrent();

        List<UserTemperatureProfile> profiles = onlineUsers.stream()
                .map(voteRepository::queryTemperatureProfile)
                .filter(votes -> !votes.isEmpty())
                .map(votes -> new PredictionPackage(votes, current))
                .map(predictionClient::predictTemperature)
                .collect(Collectors.toList());

        Double[] resultKnn = profiles.stream()
                .map(profile -> profile.getTemperatura())
                .collect(Collectors.toList()).toArray(new Double[]{});

        Arrays.sort(resultKnn);
        Double temperaturaMedianaKNN = 0d;

        if(resultKnn.length > 0) {
            temperaturaMedianaKNN = (resultKnn[resultKnn.length / 2] + resultKnn[(resultKnn.length - 1) / 2]) / 2;
        }

        RecomendacaoPackage recomendacaoPackage = new RecomendacaoPackage();
        recomendacaoPackage.setTimeStamp(Calendar.getInstance().getTime());
        if(profiles.isEmpty()){
            edgeClient.shutdownAir();
            recomendacaoPackage.setDeligar(true);
        } else {
            recomendacaoPackage.setDeligar(false);
            recomendacaoPackage.setRecomendacaoList(consensusClient.getAllRecommendation(profiles));
        }

        recomendacaoPackage.getRecomendacaoList()
                .add(new Recomendacao("Knn", recomendacaoPackage.getTimeStamp(), temperaturaMedianaKNN));

        Configuracao finalConfiguracao = configuracao;
        recomendacaoPackage.setUserTemperatureProfiles(profiles);
        recomendacaoPackage.setTemperaturaUtilizada(recomendacaoPackage.getRecomendacaoList()
                .stream()
                .filter(recomendacao ->
                        Objects.equals(recomendacao.getNameAlgorithms(),
                                finalConfiguracao.getAlgorithmsType().name()))
                .findFirst()
                .get().getConsenso());

        recomendacaoPackageRepository.save(recomendacaoPackage);

    }
}
