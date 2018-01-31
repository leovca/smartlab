package smartlab.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserTemperatureProfile {

    @Id
    @GeneratedValue
    private Integer id;

    private Double temperatura;

    private Integer idUsuario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_prefile_id")
    List<Temperature> votes;

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

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
