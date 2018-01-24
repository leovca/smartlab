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
    private Vote consenso;


    public Recomendacao(String nameAlgorithms, Vote consenso) {
        timeStamp = Timestamp.valueOf(LocalDateTime.now());
        this.nameAlgorithms = nameAlgorithms;
        this.consenso = consenso;
    }

    /**
     * @return the timeStamp
     */
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the consenso
     */
    public Vote getConsenso() {
        return consenso;
    }

    /**
     * @param consenso the consenso to set
     */
    public void setConsenso(Vote consenso) {
        this.consenso = consenso;
    }

    /**
     * @return the nameAlgorithms
     */
    public String getNameAlgorithms() {
        return nameAlgorithms;
    }

    /**
     * @param nameAlgorithms the nameAlgorithms to set
     */
    public void setNameAlgorithms(String nameAlgorithms) {
        this.nameAlgorithms = nameAlgorithms;
    }

   

   
  
}
