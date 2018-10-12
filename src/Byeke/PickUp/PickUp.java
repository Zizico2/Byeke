package Byeke.PickUp;

import Byeke.Bike.Bike;
import Byeke.Park.Park;

public interface PickUp extends PickUpInfo {

    int pickDown(Park finalPark, int time);
}
