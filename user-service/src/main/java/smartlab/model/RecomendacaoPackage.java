package smartlab.model;

import java.util.Date;
import java.util.List;


public class RecomendacaoPackage {

    Integer id;

    private Date timeStamp;

    private Double temperaturaUtilizada;


    private Configuracao configuracao;

    private Boolean deligar;

    List<Recomendacao> recomendacaoList;

    List<UserTemperatureProfile> userTemperatureProfiles;

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

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
