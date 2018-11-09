package Exceptions;

public class LowBalanceException extends RuntimeException {
    public LowBalanceException() {
        super("Saldo insuficiente.");
    }
}
