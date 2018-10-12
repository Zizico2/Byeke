package Exceptions;

public class InexistantUserIdException extends RuntimeException {
    public InexistantUserIdException(){
        super("Utilizador inexistente.");
    }
}
