package Byeke.Bike;

import Byeke.PickUp.PickUpInfo;
import dataStructures.Iterator;

public interface BikeInfo extends java.io.Serializable{

    String getId();

    String getPlate();

    Iterator<PickUpInfo> getArchivedPickUps();

    boolean isMoving();

    boolean hasPickUps();

    boolean isOnFirstPickUp();
}
