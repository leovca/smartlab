package smartlab.model;

import java.util.List;

public class PredictionPackage {
    List<UserPreference> voteList;
    UserPreference currente;
    Integer idUsuario;

    AlgortimoPreference algortimoPreference;

    Integer knn;
    Double suavizacao;

    public PredictionPackage() {
    }

    public Integer getKnn() {
        return knn;
    }

    public void setKnn(Integer knn) {
        this.knn = knn;
    }

    public Double getSuavizacao() {
        return suavizacao;
    }

    public void setSuavizacao(Double suavizacao) {
        this.suavizacao = suavizacao;
    }

    public PredictionPackage(List<UserPreference> voteList, UserPreference currente,
                             AlgortimoPreference algortimoPreference,
                             Integer knn, Double suavizacao) {
        this.voteList = voteList;
        this.currente = currente;
        this.idUsuario = voteList.get(0).getUserId();
        this.algortimoPreference = algortimoPreference;
        this.knn = knn;
        this.suavizacao = suavizacao;
    }

    public AlgortimoPreference getAlgortimoPreference() {
        return algortimoPreference;
    }

    public void setAlgortimoPreference(AlgortimoPreference algortimoPreference) {
        this.algortimoPreference = algortimoPreference;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<UserPreference> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<UserPreference> voteList) {
        this.voteList = voteList;
    }

    public UserPreference getCurrente() {
        return currente;
    }

    public void setCurrente(UserPreference currente) {
        this.currente = currente;
    }
}
