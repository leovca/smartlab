package smartlab.algoritmos;

import smartlab.model.User;
import smartlab.model.Vote;

import java.util.ArrayList;
import java.util.List;

public class AverageWithoutMisery extends Algorithms {

    public Vote GetAll(List<User> list, double limit) {

        double sum;
        double media;
        boolean isLimit= false;
        
        List<Vote> ratingsToRotulo;
        Vote voteTemp;
        List<Vote> listVoteTemp = new ArrayList<>();

        for (Double rotulo : getRotulos(list)) {
            ratingsToRotulo = getVotesInRotulo(list, rotulo);

            sum=0;
            for (Vote v : ratingsToRotulo) {
                sum += v.getRating();
                if (v.getRating() < limit) {
                    isLimit=true;
                    break;
                }
            }

            if (!isLimit) {
               media = sum/ratingsToRotulo.size();
            } else {
                media=-1;
                isLimit= false;
            }

            voteTemp = new Vote();
            voteTemp.setRating(media);
            voteTemp.setRotulo(rotulo);
            listVoteTemp.add(voteTemp);
         }
        

       return listVoteTemp.stream()
               .max((o1, o2) -> Double.compare(o1.getRating(), o2.getRating()))
               .get();
        
    }

    @Override
    Vote getAll(List<User> usuarios) {return null;}

    @Override
    public Vote calcRecomendacao(List<User> list) {
        return GetAll(list, 0.1);
    }


}
