package smartlab.model;

import java.util.List;

public class PredictionPackage {
    List<Vote> voteList;
    Vote currente;

    public PredictionPackage() {
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }

    public Vote getCurrente() {
        return currente;
    }

    public void setCurrente(Vote currente) {
        this.currente = currente;
    }
}