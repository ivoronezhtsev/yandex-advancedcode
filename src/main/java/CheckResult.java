public class CheckResult {
    private final Result result;
    private final String reason;

    public CheckResult(Result result, String reason) {
        this.result = result;
        this.reason = reason;
    }

    public CheckResult(Result result) {
        this.result = result;
        this.reason = "";
    }

    public Result getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }
}
