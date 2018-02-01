package smartlab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import smartlab.model.UserPreference;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class SimulatorPreference {

    @GetMapping("user/data/preference")
    public List<UserPreference> putData(){
        List<UserPreference> userPreferences = new ArrayList<>();
        Random randomGaus =  new Random();
        boolean perfil = false;

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
                    vote =  randomGaus.nextInt(10) + 16;
                    user.setVote(vote);
                    perfil = false;
                }else{
                    vote = randomGaus.nextInt(10) + 16;
                    user.setVote(vote);
                    perfil = true;
                }

                System.out.println("Voto: "+ vote);
                userPreferences.add(user);
            }
        }

        return userPreferences;
    }

    /*
    17 - 22 frio
    23 - 25 normal
    26 - 30 quente
    */
    public int perfilFrio(double internal, double external){
        Random random =  new Random();

        int vote;
        if(external <= 22 && internal <= 22 )
            vote = random.nextInt(30);


        return 0;
    }

    /*
     17 - 22 frio
     23 - 25 normal
     26 - 30 quente
     */
    public int perfilQuente(double internal, double external){

        return 0;
    }

    public double getExterno(){
        return 0;
    }


}