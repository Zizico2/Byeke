package Exceptions;

public class BikeHasNoPickUpsException extends RuntimeException {
    public BikeHasNoPickUpsException() {
        super("Bicicleta nao foi utilizada.");
    }
}
