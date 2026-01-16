import java.math.BigDecimal;

public class Limit {
    private BigDecimal dailyAmount;
    private BigDecimal maxForOperation;

    public BigDecimal getDailyAmount() {
        return dailyAmount;
    }

    public BigDecimal getMaxForOperation() {
        return maxForOperation;
    }

    public Limit(BigDecimal dailyAmount, BigDecimal maxForOperation) {
        this.dailyAmount = dailyAmount;
        this.maxForOperation = maxForOperation;
    }
}
