package smartlab.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smartlab.model.User;
import smartlab.model.UserDataToPredict;
import smartlab.model.UserStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class UserController {

    List<User> users;

    protected Logger logger = Logger.getLogger(UserController.class.getName());

    public UserController() {
        users = new ArrayList<>();

        users.add(new User(1, "Usuario 1", "123", UserStatus.ON));
        users.add(new User(2, "Usuario 2", "123123", UserStatus.ON));
        users.add(new User(2, "Usuario 3", "3657367", UserStatus.OFF));
        users.add(new User(3, "Usuario 4", "56483456s", UserStatus.OFF));
        users.add(new User(4, "Usuario 5", "34536763", UserStatus.OFF));
    }

    @RequestMapping("/users")
    public List<User> findAll(){
        logger.info("findAll()");
        return users;
    }

    @RequestMapping("/users/1/to_predict")
    public List<UserDataToPredict> getUserDetails(){
        logger.info("getUserDetails(1)");

        List<UserDataToPredict> userDataToPredicts = new ArrayList<UserDataToPredict>(){{
            add(new UserDataToPredict(8, 28f, 23f, 4,23));
            add(new UserDataToPredict(8, 30f, 23f, 8,22));
            add(new UserDataToPredict(9, 28f, 23f, 4,23));
            add(new UserDataToPredict(10, 25f, 24f, 6,23));
            add(new UserDataToPredict(12, 27f, 22f, 7,23));
            add(new UserDataToPredict(14, 28f, 23f, 5,23));
            add(new UserDataToPredict(18, 24f, 23f, 4,25));
        }};

        return userDataToPredicts;
    }

    @RequestMapping("/users/on")
    public List<User> findOnlineUsers(){
        logger.info("findOnlineUsers()");
        return users.stream()
                .filter(user -> user.isON())
                .collect(Collectors.toList());

    }
}
