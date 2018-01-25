package smartlab.algoritmos;

import smartlab.model.User;
import smartlab.model.Vote;

import java.util.ArrayList;
import java.util.List;

public class Multiplicative extends Algorithms {
    
    /* Multiplies individual ratings */
    @Override
    Vote getAll(List<User> usuarios) {

        double sum;
        List<Vote> ratingsToRotulo;
        Vote voteTemp;
        List<Vote> listVoteTemp = new ArrayList<>();

        for (Double rotulo : getRotulos(usuarios)) {
            ratingsToRotulo = getVotesInRotulo(usuarios, rotulo);

            sum=1;
            for (Vote v : ratingsToRotulo) {
                sum *= v.getRating();

            }

            voteTemp = new Vote();
            voteTemp.setRating(sum);
            voteTemp.setRotulo(rotulo);
            listVoteTemp.add(voteTemp);
        }


        return listVoteTemp.stream()
                .max((o1, o2) -> Double.compare(o1.getRating(), o2.getRating()))
                .get();

    }


    
}
