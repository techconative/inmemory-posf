package exception;

/** InvalidCriteriaException class thrown when criteria provided is incorrect */
public class InvalidCriteriaException extends RuntimeException {

    /**
     * InvalidCriteriaException with error message
     *
     * @param error message
     * @since 1.0.0
     */
    public InvalidCriteriaException(String error) {
        super(error);
    }

    /**
     * InvalidCriteriaException with error message and trace
     *
     * @param error message
     * @param cause stack trace
     * @since 1.0.0
     */
    public InvalidCriteriaException(String error, Throwable cause) {
        super(error, cause);
    }
}
