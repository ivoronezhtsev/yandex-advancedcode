import java.util.List;
import java.util.UUID;

public interface UserLimitsService {
    List<Limit> findAll();
    Limit findByUserId(UUID userId);
}
