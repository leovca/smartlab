package smartlab.model;

/* 
    [Vote]: Armazena o voto do usuário e um atributo 
    [ScaleValue]: Armazena o valor da escala referente ao referido voto 
 */
public class Vote {
   
    private double Vote;
    private double ScaleValue;

    public double getVote() {
        return Vote;
    }

    public void setVote(double Vote) {
        this.Vote = Vote;
    }

    public double getScaleValue() {
        return ScaleValue;
    }

    public void setScaleValue(double ScaleValue) {
        this.ScaleValue = ScaleValue;
    }

   
    
}
