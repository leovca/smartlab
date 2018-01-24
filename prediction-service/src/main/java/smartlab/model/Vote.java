package smartlab.model;

public class Vote {

    String rotulo;
    Double rating;

    public Vote() {
    }

    public Vote(String rotulo, Double rating) {
        this.rotulo = rotulo;
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
}
