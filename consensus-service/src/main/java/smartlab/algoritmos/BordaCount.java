
package br.com.ufba.grouprecommendation.algoritmos;

import br.com.ufba.grouprecommendation.model.User;
import br.com.ufba.grouprecommendation.model.Vote;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

 public class BordaCount  extends Algorithms {

     /*
        Counts points from items’ rankings in
        the individuals’ preference lists, with
        bottom item getting 0 points, next one
        up getting one point, etc
     */
     
    public List<User>GetAll(List<User> list) {
        
        double point=0;
        double sum_points=0;
        double count_points=0;
        long count_repeat;
        double result;
        
        Integer idx1=0;
        Integer idx2=0;
        Integer idx3=0;
        
        List<Object> index_controle = new ArrayList<>();
        Object order_votes;
                
        for (User user : list) {
            
            order_votes = user.getVote().stream().map(v-> v.getVote()).sorted().collect(Collectors.toList());
            
            for (Object v : (List<Object>) order_votes) {
             
                final Integer I = idx1;
                if (index_controle.stream().filter(p-> p.equals(I)).count() == 0) { /* Verifica se o valor já foi ajustado [index_controle: grava os indices que foram ajustados]  */
                    
                    /* Localiza repetições e grava os indices dos mesmos */
                    count_repeat=0;idx3=0;
                    for (Object v3 : (List<Object>) order_votes) {
                        if (v3.equals(v)) {
                            count_repeat+=1;
                            index_controle.add(idx3);
                        }
                        idx3+=1;
                    }
                    
                    /* Calculo da Borda */
                    if (count_repeat>1) {
                        sum_points=0;
                        if (idx1==0){count_points=-1;} else {count_points = point;} /* Menor valor é semrpe zero */
                        for (int c=1;c<count_repeat+1;c++) {
                            count_points+=1;
                            sum_points+=count_points;
                        }
                        result=((double) sum_points)/((double) count_repeat);
                        point=count_repeat;
                    } else {
                        /* Menor valor é semrpe zero */
                        if (idx1==0){
                            result=0.0;
                        } else {
                            result=((double) point);
                            point+=1;  
                        }
                    }
                    
                    /* Atualzia os dados da lista original */
                    idx2=0;
                    for (Vote v2 : user.getVote()) {
                        if (v2.getVote() == ((double) v)) {
                            v2.setVote(result);
                        }
                        idx2+=1;
                    }
                }
                idx1+=1;
            }
            /* Troca de usuário */
            idx1=0;
            sum_points=0;
            count_points=0;
            index_controle.clear();
            point=0;
   
        }
        /* Total */
        AlgorithmsFactory f = new AlgorithmsFactory();
        Summarized SUM;
        SUM = (Summarized) f.getAlgorithm(AlgorithmsType.Type.Summarized);
        
        return SUM.GetAll(list,"BordaCount");
        
    }
    
    public Vote GetResult(List<User> list) {
        
            User user_filter;
            list = GetAll(list);

            user_filter = list.stream().filter(p-> p.getName().equals("BordaCount")).collect(Collectors.toList()).get(0);
            Object temp = user_filter.getVote().parallelStream().max(Comparator.comparing(p-> ((Vote) p).getVote())).get();
            return ((Vote) temp);

        }
        
        
    }
     
    
    

