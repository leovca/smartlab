package smartlab;

public class ClassificationResult {
    private int numberOfPatterns;
    private int positive;
    private float executionTime;

    public ClassificationResult() {
        this.numberOfPatterns = 0;
        this.positive = 0;
    }

    public void incrementTotal() {
        this.numberOfPatterns++;
    }

    public void incrementPositive() {
        this.positive++;
    }

    public int getNumberOfPatterns() {
        return numberOfPatterns;
    }

    public int getPositive() {
        return positive;
    }

    public float getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(float executionTime) {
        this.executionTime = executionTime;
    }
}