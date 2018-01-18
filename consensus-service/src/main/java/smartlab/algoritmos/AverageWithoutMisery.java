package br.com.ufba.grouprecommendation.algoritmos;

import br.com.ufba.grouprecommendation.model.User;
import br.com.ufba.grouprecommendation.model.Vote;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AverageWithoutMisery extends Algorithms {
 
    /* Averages individual ratings, after excluding
       items with individual ratings below a 
       certain threshold (say 4). 
    */
 
    public List<User> GetAll(List<User> list, double limit) {

        double sum=0.0;
        double media=0.0;
        boolean isLimit= false;
        
        List<Vote> votes_filter;
        Vote vote_temp;
        List<Vote> list_vote_temp = new ArrayList<>();

        List<Double> scale = list.stream().map(p -> 
                p.getVote().stream().map(v -> v.getScaleValue()).collect(Collectors.toList())).collect(Collectors.toList()).get(0);
        
        for (Object s : scale) {

            votes_filter = list.stream().map(p -> 
                p.getVote().stream().filter( v -> String.valueOf(v.getScaleValue()).equals(String.valueOf(s))).collect(Collectors.toList()).get(0)
            ).collect(Collectors.toList());
             
            for (Vote v : votes_filter) {
                sum += ((double) v.getVote());
                if (v.getVote() < limit) {
                    isLimit=true;
                    break;
                }
            }
           
            if (!isLimit) {
               media = (double) (sum /( (double) votes_filter.size()));  
            } else {
                media=-1;
                isLimit= false; 
            }
            sum=0;
            
            /* Adiciona resultdo da análise a lista de votos */
            vote_temp= new Vote();
            vote_temp.setScaleValue((double) s);
            vote_temp.setVote(media);
            list_vote_temp.add(vote_temp);
         }
        
        /* Cria um novo item na lista com o reusltado da análise */
        User new_line = new User();
        new_line.setName("Average Without Misery");
        new_line.setVote(list_vote_temp);
        list.add(new_line);

        return list;
        
    }
    
    public Vote GetResult(List<User> list, double limit) {
        
        User user_filter;
        list = GetAll(list, limit);

        user_filter = list.stream().filter(p-> p.getName().equals("Average Without Misery")).collect(Collectors.toList()).get(0);
        Object temp = user_filter.getVote().parallelStream().max(Comparator.comparing(p-> ((Vote) p).getVote())).get();
        return ((Vote) temp);
        
    }
    

    public void calcAverageWithoutMisery(List<User> listUser, double limit){
        
        
        
        
    }


}
