package smartlab.model;

import java.util.List;

public class User {
    
    
    private String name; /* Nome do usuáiro */
    private List<Vote> votes;

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> Vote) {
        this.votes = Vote;
    }

      
       
        
        
}
