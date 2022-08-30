package exception;

public class InvalidCriteriaException extends RuntimeException{
    public InvalidCriteriaException(String error, Throwable cause) {
        super(error, cause);
    }
}
