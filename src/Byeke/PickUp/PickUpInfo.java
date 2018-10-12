package Byeke.PickUp;

import Byeke.Park.ParkInfo;

public interface PickUpInfo {

    ParkInfo getInitialParkInfo();

    ParkInfo getFinalParkInfo();

    int getTime();

    int getDelay();

    int getCost();

}
