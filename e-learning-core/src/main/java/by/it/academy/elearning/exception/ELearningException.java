package by.it.academy.elearning.exception;

/**
 * General E-Learning Exception
 */
public class ELearningException extends RuntimeException {

    public ELearningException(String message) {
        super(message);
    }

    public ELearningException(String message, Throwable cause) {
        super(message, cause);
    }
}
