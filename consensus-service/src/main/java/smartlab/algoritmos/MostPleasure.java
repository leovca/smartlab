package smartlab.algoritmos;

import smartlab.model.User;
import smartlab.model.Vote;

import java.util.List;

public class MostPleasure extends Algorithms {
    /* Takes the maximum of individual ratings */
     public Vote getAll(List<User> list) {
        return list.stream()
                .flatMap(user -> user.getVotes().stream())
                .max((o1, o2) -> Double.compare(o1.getRating(), o2.getRating()))
                .get();
    }
}
