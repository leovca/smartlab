package smartlab.model;


public class Temperature {

    private Integer id;

    Double rotulo;
    Double rating;

    public Temperature() {
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getRotulo() {
        return rotulo;
    }

    public void setRotulo(Double rotulo) {
        this.rotulo = rotulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
