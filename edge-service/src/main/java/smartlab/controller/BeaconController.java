package smartlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smartlab.model.UserPresence;
import smartlab.repository.UserPresenceRepository;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class BeaconController {

    @Autowired
    UserPresenceRepository userPresenceRepository;

    Logger logger = Logger.getLogger(BeaconController.class.getName());

    @PostMapping("/presence")
    public void notifyPresence(@RequestBody UserPresence userPresence){
        userPresence.setTimestamp(Calendar.getInstance().getTime());
        userPresenceRepository.save(userPresence);
        logger.info(userPresence.toString());
    }

    @GetMapping("/online")
    public List<Integer> online(){
        return userPresenceRepository.queryOnlineUsers(Calendar.getInstance().getTime());
    }



}
