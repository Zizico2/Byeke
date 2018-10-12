package Byeke.Bike;

import Byeke.PickUp.PickUpInfo;
import dataStructures.Iterator;

public interface BikeInfo{

    String getId();

    String getPlate();

    Iterator<PickUpInfo> getArchivedPickUps();

    boolean isMoving();

    boolean hasPickUps();
}
