package smartlab.algoritmos;

import smartlab.model.User;
import smartlab.model.Vote;

import java.util.List;

public class LeastMisery extends Algorithms {
    
    /* 
    Takes the minimum of individual ratings
    */
    @Override
   public Vote getAll(List<User> usuarios) {
        return usuarios.stream()
            .flatMap(user -> user.getVotes().stream())
            .min((o1, o2) -> Double.compare(o1.getRating(), o2.getRating()))
            .get();
    }


}
