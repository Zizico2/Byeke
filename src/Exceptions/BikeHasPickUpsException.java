package Exceptions;

public class BikeHasPickUpsException extends RuntimeException {
    public BikeHasPickUpsException(){
        super("Bicileta ja foi utilizada.");
    }
}
