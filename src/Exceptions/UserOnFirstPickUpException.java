package Exceptions;

public class UserOnFirstPickUpException extends RuntimeException {
    public UserOnFirstPickUpException(){
        super("Utilizador em primeiro pickup.");
    }
}
