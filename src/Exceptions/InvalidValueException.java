package Exceptions;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(){
        super("Dados invalidos.");
    }
}
