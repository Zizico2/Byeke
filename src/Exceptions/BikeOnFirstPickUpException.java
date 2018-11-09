package Exceptions;

public class BikeOnFirstPickUpException extends RuntimeException {
    public BikeOnFirstPickUpException() {
        super("Bicicleta em movimento em primeiro pickup.");
    }
}
