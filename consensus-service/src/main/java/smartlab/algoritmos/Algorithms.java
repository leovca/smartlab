package smartlab.algoritmos;

import smartlab.model.Recomendacao;
import smartlab.model.User;
import smartlab.model.Vote;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Algorithms {
    abstract Vote getAll(List<User> usuarios);

    public Recomendacao getRecomendacao(List<User> usuarios){
        Recomendacao recomendacao =
                new Recomendacao(this.getClass().getName(),
                                        this.calcRecomendacao(usuarios));

        return recomendacao;
    }

    List<Vote> getVotesInRotulo(List<User> usuarios, Double rotulo){
        return usuarios.stream()
                .map(usuario -> usuario.getVote()
                        .stream()
                        .filter(v -> v.getRotulo() == rotulo)
                        .findFirst().get()
                ).collect(Collectors.toList());
    }

    List<Double> getRotulos(List<User> list){
        return list.stream()
                .map(usuario ->
                        usuario.getVote()
                                .stream()
                                .map(v -> v.getRotulo())
                                .collect(Collectors.toList()))
                .findFirst().get();
    }

    public Vote calcRecomendacao(List<User> list){
        return getAll(list);
    }


}
