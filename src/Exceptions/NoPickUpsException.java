package Exceptions;

public class NoPickUpsException extends RuntimeException {
    public NoPickUpsException() {
        super("Nao foram efetuados pickups.");
    }
}
