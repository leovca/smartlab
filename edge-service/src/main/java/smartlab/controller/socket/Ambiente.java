package smartlab.controller.socket;

import smartlab.model.Recomendacao;

public class Ambiente {
    private String external;
    private String internal;
    private Double airTemp;
    private Recomendacao recomendacao;

    public Ambiente(String external, String internal, Double airTemp, Recomendacao recomendacao) {
        this.external = external;
        this.internal = internal;
        this.airTemp = airTemp;
        this.recomendacao = recomendacao;
    }

    public Recomendacao getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(Recomendacao recomendacao) {
        this.recomendacao = recomendacao;
    }

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }

    public String getInternal() {
        return internal;
    }

    public void setInternal(String internal) {
        this.internal = internal;
    }

    public Double getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(Double airTemp) {
        this.airTemp = airTemp;
    }
}
