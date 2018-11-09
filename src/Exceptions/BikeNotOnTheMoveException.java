package Exceptions;

public class BikeNotOnTheMoveException extends RuntimeException {
    public BikeNotOnTheMoveException() {
        super("Bicicleta parada.");
    }
}
