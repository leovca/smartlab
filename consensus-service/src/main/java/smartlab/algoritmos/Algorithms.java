package smartlab.algoritmos;

import smartlab.model.User;
import smartlab.model.Vote;

import java.util.List;

public abstract class Algorithms {
      
    AlgorithmsType Type;

    public Vote GetResult(List<User> list, double limit){
        throw new RuntimeException("Essa aprada não ser ver para nada");
    };

    public abstract Vote GetResult(List<User> list);

}
