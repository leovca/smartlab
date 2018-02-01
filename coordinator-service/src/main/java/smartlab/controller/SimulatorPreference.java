package smartlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import smartlab.model.UserPreference;
import smartlab.repository.VoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class SimulatorPreference {

    @Autowired
    VoteRepository voteRepository;

    @GetMapping("user/data/preference")
    public List<UserPreference> putData(){
        voteRepository.deleteAll();

        List<UserPreference> userPreferences = new ArrayList<>();
        Random randomGaus =  new Random();
        boolean perfil = true;

        for (int i=1; i <= 8; i++) {

            for (int j=0; j < 200; j++) {

                UserPreference user = new UserPreference();
                user.setUserId(i);

                double valorExterno;
                do {
                    valorExterno = randomGaus.nextGaussian() * 10 + 10;
                } while (valorExterno <= 17);

                System.out.println("Valor externo: "+(float) valorExterno);
                user.setExternalTemperature((float) valorExterno);


                double valorInterno;
                do {
                    valorInterno = randomGaus.nextGaussian() * 10 + 10 - valorExterno/2;
                } while (valorInterno <= 17);

                System.out.println("Valor interno: "+ (float) valorInterno);
                user.setInternalTemperature((float) valorInterno);


                double onlineUSer;
                do {
                    onlineUSer = randomGaus.nextGaussian() * 1 + 4;
                } while (onlineUSer <= 0);

                System.out.println("Usuarios online: "+ (int) onlineUSer);
                user.setOnlineUsers((int) onlineUSer);


                int vote;
                if(perfil) {
                    vote =  perfilFrio(user.getInternalTemperature(), user.getExternalTemperature());
                    user.setVote(vote);

                }else{
                    vote = perfilQuente(user.getInternalTemperature(), user.getExternalTemperature());
                    user.setVote(vote);

                }

                System.out.println("Voto: "+ vote);
                userPreferences.add(user);
            }

            if(perfil) {
                perfil = false;
            }else{
                perfil = true;
            }
        }

        voteRepository.save(userPreferences);
        return userPreferences;
    }

    /*
    17 - 22 frio
    23 - 25 normal
    26 - 30 quente
    */
    public int perfilFrio(double internal, double external){
        Random random =  new Random();

        int vote = (int) internal;
        if(internal <= 22) {
            vote = random.nextInt(7) + 23;
        }

        return vote;
    }

    /*
     17 - 22 frio
     23 - 25 normal
     26 - 30 quente
     */
    public int perfilQuente(double internal, double external){
        Random random =  new Random();

        int vote = (int) internal;
        if(internal >= 25) {
            vote = random.nextInt(7) + 17;
        }

        return vote;
    }

    public double getExterno(){

        return 0;
    }


}
