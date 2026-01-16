import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public class PaymentsChecker {
    private final PaymentsHistoryService paymentsHistoryService;
    private final UserLimitsService userLimitsService;

    public PaymentsChecker(PaymentsHistoryService paymentsHistoryService, UserLimitsService userLimitsService) {
        this.paymentsHistoryService = paymentsHistoryService;
        this.userLimitsService = userLimitsService;
    }

    public CheckResult checkPayment(Payment payment) {
        // Если платеж меньше суточного лимита и сумма в сутки больше платежа
        Limit limit = userLimitsService.findByUserId(payment.getUserId());
        Deque<Payment> paymentsDeque = paymentsHistoryService.getHistoryFor24Hours().get(payment.getUserId());
        List<BigDecimal> amounts = new ArrayList<>();
        for(Payment paymentInHistory: paymentsDeque) {
            amounts.add(paymentInHistory.getAmount());
        }
        BigDecimal totalFor24 = amounts.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        boolean  lessThanAmount = payment.getAmount().compareTo(limit.getDailyAmount()) < 0;
        boolean lessThanTotal = totalFor24.add(payment.getAmount()).compareTo(limit.getDailyAmount()) < 0;
        if(lessThanAmount && lessThanTotal) {
            return new CheckResult(Result.SUCCES);
        } else if(!lessThanAmount && lessThanTotal) {
            return new CheckResult(Result.FAIL, "Сумма превышает лимит по операции");
        } else if(!lessThanAmount && !lessThanTotal) {

        }
        return new CheckResult(Result.FAIL, "Сумма превышает лимит");
    }
}
