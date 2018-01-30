package smartlab.model;

import java.util.List;

public class UserTemperatureProfile {

    private Integer id;

    private Integer idUsuario;

    List<Temperature> votes;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Temperature> getVotes() {
        return votes;
    }

    public void setVotes(List<Temperature> votes) {
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
