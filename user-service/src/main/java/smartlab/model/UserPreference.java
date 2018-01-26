package smartlab.model;

import java.util.Calendar;
import java.util.Date;

public class UserPreference {

    private Integer idVote;
    private Integer userId;
    private Integer onlineUsers;
    private Integer vote;
    private Float externalTemperature;
    private Float internalTemperature;
    private Integer hour;

    public UserPreference() {
    }

    private Date created = new Date();

    public Integer getIdVote() {
        return idVote;
    }

    public void setIdVote(Integer idVote) {
        this.idVote = idVote;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(Integer onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public Float getExternalTemperature() {
        return externalTemperature;
    }

    public void setExternalTemperature(Float externalTemperature) {
        this.externalTemperature = externalTemperature;
    }

    public Float getInternalTemperature() {
        return internalTemperature;
    }

    public void setInternalTemperature(Float internalTemperature) {
        this.internalTemperature = internalTemperature;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

}
