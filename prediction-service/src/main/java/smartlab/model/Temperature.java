package smartlab.model;

public class Temperature {

    String temperature;
    Double rating;

    public Temperature() {
    }

    public Temperature(String temperature, Double rating) {
        this.temperature = temperature;
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
