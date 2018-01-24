package smartlab.model;

import java.util.List;

public class PredictionPackage {
    List<UserPreference> voteList;
    UserPreference currente;

    public PredictionPackage() {
    }

    public PredictionPackage(List<UserPreference> voteList, UserPreference currente) {
        this.voteList = voteList;
        this.currente = currente;
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
