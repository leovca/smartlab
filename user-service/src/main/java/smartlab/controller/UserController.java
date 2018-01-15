package smartlab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("user/online")
    public Integer[] getOnlineUsers(){
        //Deve retornar o id dos usuários online
        return new Integer[]{1,2,3,4,5};
    }
}
