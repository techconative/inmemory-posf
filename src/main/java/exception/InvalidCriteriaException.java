package exception;

/**
 * InvalidCriteriaException class thrown when criteria provided is incorrect
 */
public class InvalidCriteriaException extends RuntimeException{

    /**
     * <p>InvalidCriteriaException with error message</p>
     * @param error message
     * @since 1.0.0
     */
    public InvalidCriteriaException(String error) {
        super(error);
    }

    /**
     * <p>InvalidCriteriaException with error message and trace</p>
     * @param error message
     * @param cause stack trace
     * @since 1.0.0
     */
    public InvalidCriteriaException(String error, Throwable cause) {
        super(error, cause);
    }
}
