package Exceptions;

public class DuplicateParkIdException extends RuntimeException {
    public DuplicateParkIdException() {
        super("Parque existente.");
    }
}
