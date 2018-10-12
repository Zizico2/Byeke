package Byeke.PickUp;

import Byeke.Bike.Bike;
import Byeke.Bike.BikeInfo;
import Byeke.Park.ParkInfo;
import Byeke.User.UserInfo;

public interface PickUpInfo extends java.io.Serializable{

    ParkInfo getInitialParkInfo();

    ParkInfo getFinalParkInfo();

    int getTime();

    int getDelay();

    int getCost();

    UserInfo getUserInfo();

    BikeInfo getBikeInfo();

    Bike getBike();
}
