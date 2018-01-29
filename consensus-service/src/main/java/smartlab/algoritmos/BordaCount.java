
package smartlab.algoritmos;

import smartlab.model.User;
import smartlab.model.Vote;


import java.util.*;
import java.util.stream.Collectors;


public class BordaCount  extends Algorithms {

     /*
        Counts points from items’ rankings in
        the individuals’ preference lists, with
        bottom item getting 0 points, next one
        up getting one point, etc
     */
     @Override
     public Vote getAll(List<User> usuarios) {


         for (User user : usuarios) {
             List<Vote> result = user.getVotes().stream().sorted(Comparator.comparing(Vote::getRating)).collect(Collectors.toList());
             List<Vote> calc = new ArrayList<>();
             for (int i=1; i <= result.size();i++) {
                 double sum = 0;
                 int count = 1;
                 boolean media = false;
                 Set<Integer> index = new HashSet<>();
                     while (i < result.size() && result.get(i-1).getRating() == result.get(i).getRating()) {
                         count++;
                         index.add(i-1);
                         index.add(i);
                         i++;
                         media = true;
                     }


                 if(media) {

                     for (Integer indice : index) {
                         sum += indice;
                     }
                     sum = sum/count;
                     for (Integer ind : index) {
                         Vote vote =  new Vote();
                         vote.setRotulo(result.get(ind).getRotulo());
                         vote.setRating(sum);
                         calc.add(ind, vote);
                     }

                 }else{
                       Vote vote =  new Vote();
                       vote.setRotulo(result.get(i-1).getRotulo());
                       vote.setRating(i-1);
                       calc.add(i-1, vote);
                     }
             }
             user.setVotes(calc);
         }



         return getMax(usuarios);


     }

     public Vote getMax(List<User> users) {
         double sum;

         List<Vote> ratingsToRotulo;
         Vote voteMax = new Vote();
         voteMax.setRating(0);

         for (Double rotulo : getRotulos(users)) {
             ratingsToRotulo = getVotesInRotulo(users, rotulo);

             sum = 0;
             for (Vote v : ratingsToRotulo) {
                 sum += v.getRating();
             }
             if (sum >= voteMax.getRating()) {
                 voteMax.setRating(sum);
                 voteMax.setRotulo(rotulo);
             }
         }


         return voteMax;
     }
 }
     
    
    

