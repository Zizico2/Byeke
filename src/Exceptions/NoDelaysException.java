package Exceptions;

public class NoDelaysException extends RuntimeException {
    public NoDelaysException() {
        super("Nao se registaram atrasos.");
    }
}
