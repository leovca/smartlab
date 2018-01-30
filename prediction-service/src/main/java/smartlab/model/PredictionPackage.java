package smartlab.model;

import java.util.List;

public class PredictionPackage {
    List<Preference> voteList;
    Preference currente;
    Integer idUsuario;

    public PredictionPackage() {
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
