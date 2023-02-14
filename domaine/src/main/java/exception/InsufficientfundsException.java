package exception;

public class InsufficientfundsException extends RuntimeException {
    private String message;
    public InsufficientfundsException() {}
    public InsufficientfundsException(String msg) {
        super(msg);
        this.message = msg;
    }
}