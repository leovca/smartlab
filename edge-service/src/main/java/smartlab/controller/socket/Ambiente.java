package smartlab.controller.socket;

public class Ambiente {
    private String external;
    private String internal;
    private Double airTemp;

    public Ambiente(String external, String internal, Double airTemp) {
        this.external = external;
        this.internal = internal;
        this.airTemp = airTemp;
    }

    public Ambiente() {
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
