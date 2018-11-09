package Exceptions;

public class BikeHasPickUpsException extends RuntimeException {
    public BikeHasPickUpsException() {
        super("Bicicleta ja foi utilizada.");
    }
}
