package smartlab.algoritmos;

import smartlab.model.User;
import smartlab.model.Vote;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Multiplicative extends Algorithms {
    
    /* Multiplies individual ratings */
    public List<User>GetAll(List<User> list) {

        double calc=1;
        
        List<Vote> votes_filter;
        Vote vote_temp;
        List<Vote> list_vote_temp = new ArrayList<>();

        List<Double> scale = list.stream().map(p -> 
                p.getVotes().stream().map(v -> v.getRating()).collect(Collectors.toList())
        ).collect(Collectors.toList()).get(0);
        
        for (Object s : scale) {

            votes_filter = list.stream().map(p -> 
                p.getVotes().stream().filter(v -> String.valueOf(v.getRating()).equals(String.valueOf(s))).collect(Collectors.toList()).get(0)
            ).collect(Collectors.toList());
             
            for (Vote v : votes_filter) {
                System.out.println(v.getRotulo());
                if (v.getRotulo() > 0) { calc = calc * ((double) v.getRotulo());}
            }
            
            /* Adiciona resultdo da análise a lista de votos */
            vote_temp= new Vote();
            vote_temp.setRating((double) s);
            vote_temp.setRotulo(calc);
            list_vote_temp.add(vote_temp);
            calc=1;
         }
        
        /* Cria um novo item na lista com o reusltado da análise */
        User new_line = new User();
        new_line.setName("Multiplicative");
        new_line.setVotes(list_vote_temp);
        list.add(new_line);

        return list;
        
    }

    @Override
    Vote getAll(List<User> usuarios) {
        return null;
    }

    public Vote calcRecomendacao(List<User> list) {
        
        User user_filter;
        list = GetAll(list);

        user_filter = list.stream().filter(p-> p.getName().equals("Multiplicative")).collect(Collectors.toList()).get(0);
        Object temp = user_filter.getVotes().parallelStream().max(Comparator.comparing(p-> ((Vote) p).getRotulo())).get();
        return ((Vote) temp);
        
    }
    
}
