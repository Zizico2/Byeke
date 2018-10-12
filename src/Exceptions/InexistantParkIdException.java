package Exceptions;

public class InexistantParkIdException extends RuntimeException {
    public InexistantParkIdException() {
        super("Parque inextistente.");
    }
}
