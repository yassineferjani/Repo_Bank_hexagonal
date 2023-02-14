package exception;

public class TransactionNotFoundException extends RuntimeException {
    private String message;
    public TransactionNotFoundException() {}
    public TransactionNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
