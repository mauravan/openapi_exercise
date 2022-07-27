package ch.ti8m.openapi_exercise.customer.exception;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException(String msg) {
        this(msg, new Throwable());
    }

    public UsernameAlreadyExistsException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
