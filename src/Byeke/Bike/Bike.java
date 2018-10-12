package Byeke.Bike;

import Byeke.PickUp.PickUp;

public interface Bike extends BikeInfo {
    void pickUp(PickUp pickUp);
    void pickDown();

}
