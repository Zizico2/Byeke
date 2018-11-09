package Exceptions;

public class DuplicateUserIdException extends RuntimeException {
    public DuplicateUserIdException() {
        super("Utilizador existente.");
    }
}
