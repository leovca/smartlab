package smartlab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import smartlab.intercom.CoordiantorUserClient;
import smartlab.model.UserPresence;
import smartlab.repository.UserPresenceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class UserChangeCheckTask {
    private static final Logger log = Logger.getLogger(UserChangeCheckTask.class.getName());

    @Autowired
    UserPresenceRepository userPresenceRepository;

    @Autowired
    CoordiantorUserClient coordiantorUserClient;

    List<Integer> usuarios;

    public UserChangeCheckTask() {
        this.usuarios = new ArrayList<>();
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        List<Integer> currentList = userPresenceRepository.queryOnlineUsers();
        if(!listasIguais(currentList, usuarios)){
            log.info("Alteração na lista de usuários");
            coordiantorUserClient.changeDetected();
            usuarios = currentList;
        }

    }

    private Boolean listasIguais(List list1, List list2) {
        return list1.containsAll(list2) && list2.containsAll(list1);
    }
}
