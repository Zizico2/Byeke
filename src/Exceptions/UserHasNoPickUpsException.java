package Exceptions;

public class UserHasNoPickUpsException extends RuntimeException {
    public UserHasNoPickUpsException(){
        super("Utilizador nao utilizou o sistema.");
    }
}
