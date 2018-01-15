package smartlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smartlab.intercom.CoordiantorUserClient;

@RestController
public class VoteController {

    @Autowired
    CoordiantorUserClient coordiantorUserClient;

    //Recebe a temperature de preferência do usuário para o momento
    @PostMapping("{userId}/vote")
    public Integer vote(@PathVariable("userId") Integer userId, @RequestBody Integer vote){
        //Não remover essa chamada, ela aciona o serviço coordenador
        coordiantorUserClient.receiverTemperatureVote(userId, vote);

        return userId;
    }


}
