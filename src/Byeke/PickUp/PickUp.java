package Byeke.PickUp;

import Byeke.Park.Park;

public interface PickUp extends PickUpInfo {

    void pickDown(Park finalPark, int time);
}
