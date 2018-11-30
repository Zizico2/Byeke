package Byeke.Park;


/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public interface ParkInfo extends java.io.Serializable{

    String getAddress();

    boolean hasParkedBikes();

    String getId();

    int getNoPickUps();

    String getName();

    boolean hasPickUps();

    int getNoParkedBikes();
}
