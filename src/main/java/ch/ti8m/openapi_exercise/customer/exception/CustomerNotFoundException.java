package ch.ti8m.openapi_exercise.customer.exception;


public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String msg) {
        this(msg, new Throwable());
    }

    public CustomerNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
