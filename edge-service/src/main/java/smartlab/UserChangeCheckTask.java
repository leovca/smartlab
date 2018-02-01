package smartlab;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import smartlab.controller.socket.Ambiente;
import smartlab.intercom.CoordiantorUserClient;
import smartlab.model.Recomendacao;
import smartlab.model.RecomendacaoPackage;
import smartlab.repository.SensorDataRepository;
import smartlab.repository.UserPresenceRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
//import java.util.logging.Logger;

@Component
public class UserChangeCheckTask {

    private static final Logger log = Logger.getLogger(UserChangeCheckTask.class.getName());


    @Autowired
    UserPresenceRepository userPresenceRepository;

    @Autowired
    CoordiantorUserClient coordiantorUserClient;

    List<Integer> usuarios;

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    SensorDataRepository sensorDataRepository;


    public UserChangeCheckTask() {
        this.usuarios = new ArrayList<>();
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        List<Integer> currentList = userPresenceRepository.queryOnlineUsers(Calendar.getInstance().getTime());
        if(!listasIguais(currentList, usuarios)){
            log.info("Alteração na lista de usuários");
            coordiantorUserClient.changeDetected();
            usuarios = currentList;
        }

        try {
            RecomendacaoPackage recomendacao = coordiantorUserClient.getConsensusWithProfile();
            this.template.convertAndSend("/topic/observer", new Ambiente(
                    sensorDataRepository.findTopByIdSensorOrderByTimeDesc("tmp2").getValue(),
                    sensorDataRepository.findTopByIdSensorOrderByTimeDesc("tmp1").getValue(),
                    recomendacao.getTemperaturaUtilizada(),
                    recomendacao));
        } catch (Exception ex){
            log.error(ex);
        }

        this.template.convertAndSend("/topic/usuarios", this.usuarios);
    }

    private Boolean listasIguais(List list1, List list2) {
        return list1.containsAll(list2) && list2.containsAll(list1);
    }
}
