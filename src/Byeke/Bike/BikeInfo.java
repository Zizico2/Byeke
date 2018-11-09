package Byeke.Bike;

import Byeke.PickUp.PickUpInfo;
import dataStructures.Iterator;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public interface BikeInfo extends java.io.Serializable {

    String getId();

    String getPlate();

    Iterator<PickUpInfo> getArchivedPickUps();

    boolean isMoving();

    boolean hasPickUps();

    boolean isOnFirstPickUp();
}
