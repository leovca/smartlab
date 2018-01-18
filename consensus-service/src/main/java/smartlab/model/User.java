package smartlab.model;

import java.util.List;

public class User {
    
    
    private String Name; /* Nome do usuáiro */
    private List<Vote> Vote;  

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public List<Vote> getVote() {
        return Vote;
    }

    public void setVote(List<Vote> Vote) {
        this.Vote = Vote;
    }

      
       
        
        
}
