package Exceptions;

public class UserOnTheMoveException extends RuntimeException {
    public UserOnTheMoveException() {
        super("Utilizador em movimento.");
    }
}
