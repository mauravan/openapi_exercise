package ch.ti8m.openapi_exercise.customer.exception;

public class CustomerIdentifierException extends Exception {
    public CustomerIdentifierException(String msg) {
        this(msg, new Throwable());
    }

    public CustomerIdentifierException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
