package smartlab.algoritmos;

import smartlab.model.User;
import smartlab.model.Vote;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MostPleasure extends Algorithms {
    /* Takes the maximum of individual ratings */
     public Vote getAll(List<User> list) {
        return list.stream()
                .flatMap(user -> user.getVote().stream())
                .max((o1, o2) -> Double.compare(o1.getRating(), o2.getRating()))
                .get();
    }
}
