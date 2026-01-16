import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Payment {
    UUID userId;
    BigDecimal amount;
    String operationDescription;
    LocalDateTime operationTime;

    public Payment() {
    }

    public Payment(UUID userId, BigDecimal amount, String operationDescription, LocalDateTime operationTime) {

        this.userId = userId;
        this.amount = amount;
        this.operationDescription = operationDescription;
        this.operationTime = operationTime;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public LocalDateTime getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(userId, payment.userId) && Objects.equals(amount, payment.amount) && Objects.equals(operationDescription, payment.operationDescription) && Objects.equals(operationTime, payment.operationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, amount, operationDescription, operationTime);
    }
}
