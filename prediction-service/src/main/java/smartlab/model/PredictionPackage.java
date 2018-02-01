package smartlab.model;

import java.util.List;

public class PredictionPackage {
    List<Preference> voteList;
    Preference currente;
    Integer idUsuario;
    AlgortimoPreference algortimoPreference;

    Integer knn;
    Double suavizacao;


    public PredictionPackage() {
    }

    public AlgortimoPreference getAlgortimoPreference() {
        return algortimoPreference;
    }

    public void setAlgortimoPreference(AlgortimoPreference algortimoPreference) {
        this.algortimoPreference = algortimoPreference;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Preference> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Preference> voteList) {
        this.voteList = voteList;
    }

    public Preference getCurrente() {
        return currente;
    }

    public void setCurrente(Preference currente) {
        this.currente = currente;
    }
}
