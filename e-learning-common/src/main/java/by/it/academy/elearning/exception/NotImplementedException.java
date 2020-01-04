package by.it.academy.elearning.exception;

public class NotImplementedException extends ELearningException {

    public NotImplementedException() {
        super("Not Implemented yet");
    }

    public NotImplementedException(String message) {
        super(message);
    }

    public NotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }
}
