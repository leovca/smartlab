package smartlab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Configuracao {

    @Id
    @GeneratedValue
    Integer id;

    AlgorithmsType algorithmsType;
    AlgortimoPreference algortimoPreference;

    Integer knn = 3;
    Double suavizacao = 0.5;

    public Integer getKnn() {
        return knn;
    }

    public void setKnn(Integer knn) {
        this.knn = knn;
    }

    public Double getSuavizacao() {
        return suavizacao;
    }

    public void setSuavizacao(Double suavizacao) {
        this.suavizacao = suavizacao;
    }

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
