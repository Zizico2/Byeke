package Exceptions;

public class DuplicateBikeIdException extends RuntimeException {
    public DuplicateBikeIdException(){
        super("Bicicleta existente.");
    }
}
