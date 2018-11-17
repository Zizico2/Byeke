package Byeke.Park;

import Byeke.Tags.Nameable;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public interface ParkInfo extends java.io.Serializable, Nameable {

    String getAddress();

    boolean hasParkedBikes();

    String getId();

    int getNoPickUps();

    boolean hasPickUps();

    int getNoParkedBikes();
}
