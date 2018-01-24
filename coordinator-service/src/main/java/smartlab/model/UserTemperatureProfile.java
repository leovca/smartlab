package smartlab.model;

import java.util.List;

public class UserTemperatureProfile {

    List<Temperature> votes;

    public List<Temperature> getVotes() {
        return votes;
    }

    public void setVotes(List<Temperature> votes) {
        this.votes = votes;
    }
}
