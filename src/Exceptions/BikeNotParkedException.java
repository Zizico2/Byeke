package Exceptions;

public class BikeNotParkedException extends RuntimeException {
    public BikeNotParkedException() {
        super("Bicicleta nao esta em parque.");
    }
}
