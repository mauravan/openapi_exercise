package ch.ti8m.openapi_exercise.customer.exception;

public class UsernameNotMatchingException extends Exception {
    public UsernameNotMatchingException(String msg) {
        this(msg, new Throwable());
    }

    public UsernameNotMatchingException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
