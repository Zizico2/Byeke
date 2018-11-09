package Exceptions;

public class InvalidTimeException extends RuntimeException {
    public InvalidTimeException() {
        super("Dados invalidos.");
    }
}
