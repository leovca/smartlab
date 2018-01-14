package smartlab.model;

public class UserDataToPredict {
    private Integer hour;
    private Float externalTemperature;
    private Float internalTemperature;
    private Integer userCount;
    private Integer vote;

    public UserDataToPredict(Integer hour, Float externalTemperature, Float internalTemperature, Integer userCount, Integer vote) {
        this.hour = hour;
        this.externalTemperature = externalTemperature;
        this.internalTemperature = internalTemperature;
        this.userCount = userCount;
        this.vote = vote;
    }

    public UserDataToPredict() {
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
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

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}
