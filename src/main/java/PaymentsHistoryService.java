import java.util.Deque;
import java.util.Map;
import java.util.UUID;

public interface PaymentsHistoryService {

    Map<UUID, Deque<Payment>> getHistoryFor24Hours();
}
