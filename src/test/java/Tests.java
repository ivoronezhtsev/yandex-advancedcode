import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Tests {

    @Mock
    private PaymentsHistoryService paymentsHistoryService;

    @Mock
    private UserLimitsService userLimitsService;

    @Test
    void happyPath() {
        Map<UUID, Deque<Payment>> paymentsHistory = new HashMap<>();
        UUID userId = UUID.randomUUID();
        Deque<Payment> payments = new ArrayDeque<>();
        payments.push(new Payment(userId, BigDecimal.ONE, "pay for light", LocalDateTime.now()));
        paymentsHistory.put(userId, payments);
        when(userLimitsService.findByUserId(userId)).thenReturn(new Limit(BigDecimal.TEN, BigDecimal.TEN));
        when(paymentsHistoryService.getHistoryFor24Hours()).thenReturn(paymentsHistory);
        PaymentsChecker paymentsChecker = new PaymentsChecker(paymentsHistoryService, userLimitsService);
        CheckResult checkResult = paymentsChecker.checkPayment(new Payment(userId, BigDecimal.ONE, "something", LocalDateTime.now()));
        Assertions.assertEquals(Result.SUCCES, checkResult.getResult());
    }
}

