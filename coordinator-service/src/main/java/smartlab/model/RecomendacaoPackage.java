package smartlab.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class RecomendacaoPackage {

    @Id
    @GeneratedValue
    Integer id;

    private Date timeStamp;

    private Double temperaturaUtilizada;


    private Boolean deligar;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "package_id")
    List<Recomendacao> recomendacaoList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "package_id")
    List<UserTemperatureProfile> userTemperatureProfiles;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Boolean getDeligar() {
        return deligar;
    }

    public void setDeligar(Boolean deligar) {
        this.deligar = deligar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTemperaturaUtilizada() {
        return temperaturaUtilizada;
    }

    public void setTemperaturaUtilizada(Double temperaturaUtilizada) {
        this.temperaturaUtilizada = temperaturaUtilizada;
    }

    public List<Recomendacao> getRecomendacaoList() {
        return recomendacaoList;
    }

    public void setRecomendacaoList(List<Recomendacao> recomendacaoList) {
        this.recomendacaoList = recomendacaoList;
    }

    public List<UserTemperatureProfile> getUserTemperatureProfiles() {
        return userTemperatureProfiles;
    }

    public void setUserTemperatureProfiles(List<UserTemperatureProfile> userTemperatureProfiles) {
        this.userTemperatureProfiles = userTemperatureProfiles;
    }
}
