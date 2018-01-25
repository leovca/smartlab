package smartlab.model;

/* 
    [rotulo]: Armazena o rotulo
    [rating]: Armazena o valor da escala referente ao algoritmo de AM
 */
public class Vote {

    //
    private double rotulo;
    private double rating;


    public double getRotulo() {
        return rotulo;
    }

    public void setRotulo(double Vote) {
        this.rotulo = Vote;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double ScaleValue) {
        this.rating = ScaleValue;
    }

    public Vote(double rotulo, double rating) {
        this.rotulo = rotulo;
        this.rating = rating;
    }

    public Vote() {
    }
}
