package exception;

public class InsufficientfundsException extends RuntimeException {
    public InsufficientfundsException(String msg) {
        super(msg);
    }
}