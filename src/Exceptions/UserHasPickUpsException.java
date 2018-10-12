package Exceptions;

public class UserHasPickUpsException extends RuntimeException {
    public UserHasPickUpsException(){
        super("Utilizador ja utilizou o sistema.");
    }
}
