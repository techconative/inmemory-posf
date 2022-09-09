package exception;

public class InvalidCriteriaException extends RuntimeException{
    public InvalidCriteriaException(String error) {
        super(error);
    }
    public InvalidCriteriaException(String error, Throwable cause) {
        super(error, cause);
    }
}
