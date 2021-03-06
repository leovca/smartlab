package smartlab.algoritmos;

public class AlgorithmsFactory {

        public static Algorithms getAlgorithm(AlgorithmsType T) {
        
                if (T == AlgorithmsType.LeastMisery) {
                       return new LeastMisery();
                 } else if (T == AlgorithmsType.BorderCount) {
                       return new BordaCount();
                 } else if (T == AlgorithmsType.AverageWithoutMisery) {
                       return new AverageWithoutMisery();
                 } else if (T == AlgorithmsType.Multiplicative) {
                       return new Multiplicative();
                 } else if (T == AlgorithmsType.MostPleasure) {
                       return new MostPleasure();
                 } else {
                     return null;
                 }
    }
}