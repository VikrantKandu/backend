package TodayTask.exception;

public class TodayTaskException extends RuntimeException {
    private final String errorCode;

    public TodayTaskException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}