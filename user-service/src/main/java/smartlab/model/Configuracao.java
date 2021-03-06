package smartlab.model;

public class Configuracao {

    Integer id;

    AlgorithmsType algorithmsType;
    AlgortimoPreference algortimoPreference;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Configuracao(AlgorithmsType algorithmsType) {
        this.algorithmsType = algorithmsType;
    }

    public AlgorithmsType getAlgorithmsType() {
        return algorithmsType;
    }

    public void setAlgorithmsType(AlgorithmsType algorithmsType) {
        this.algorithmsType = algorithmsType;
    }

    public AlgortimoPreference getAlgortimoPreference() {
        return algortimoPreference;
    }

    public void setAlgortimoPreference(AlgortimoPreference algortimoPreference) {
        this.algortimoPreference = algortimoPreference;
    }

    public Configuracao() {
    }
}
