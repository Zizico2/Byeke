package Exceptions;

public class InexistantBikeIdException extends RuntimeException {
    public InexistantBikeIdException(){
        super("Bicicleta inexistente.");
    }
}
