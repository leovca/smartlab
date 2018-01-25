/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartlab.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 *
 * @author Brenno Mello <brennodemello.bm at gmail.com>
 */
public class Recomendacao {
    
    private String nameAlgorithms;
    private Timestamp timeStamp;
    private Double consenso;


    public Recomendacao(String nameAlgorithms, Double consenso) {
        timeStamp = Timestamp.valueOf(LocalDateTime.now());
        this.nameAlgorithms = nameAlgorithms;
    }

    public String getNameAlgorithms() {
        return nameAlgorithms;
    }

    public void setNameAlgorithms(String nameAlgorithms) {
        this.nameAlgorithms = nameAlgorithms;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getConsenso() {
        return consenso;
    }

    public void setConsenso(Double consenso) {
        this.consenso = consenso;
    }
}
