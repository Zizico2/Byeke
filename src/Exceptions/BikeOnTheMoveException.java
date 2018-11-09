package Exceptions;

public class BikeOnTheMoveException extends RuntimeException {
    public BikeOnTheMoveException() {
        super("Bicicleta em movimento.");
    }
}
