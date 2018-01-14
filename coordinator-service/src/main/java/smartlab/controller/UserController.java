package smartlab.controller;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smartlab.intercom.UserClient;
import smartlab.model.User;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class UserController {

    protected Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserClient userClient;


    @RequestMapping("/user-status-change")
    private List<User> userStatusChange(){
        logger.info("userStatusChange()");

        try {
            String script = new String(Files.readAllBytes(Paths.get(getClass().getResource("knn.R").toURI())));
            RConnection c = new RConnection("127.0.0.1", 1010);
            REXP x = c.eval(script);
            logger.info(x.asString());

        }catch (Exception ex){
            logger.info(ex.getMessage());
        }


        return userClient.findOnlineUsers()
                .stream()
                .map(user -> {
                    user.setUserDataToPredictList(userClient.getUserDetails());
                    return user;
                })
                .collect(Collectors.toList());
    }

}
