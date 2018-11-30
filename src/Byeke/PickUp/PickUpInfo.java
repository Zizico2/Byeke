package Byeke.PickUp;

import Byeke.Bike.BikeInfo;
import Byeke.Park.ParkInfo;
import Byeke.User.UserInfo;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public interface PickUpInfo extends java.io.Serializable {

    ParkInfo getInitialParkInfo();

    ParkInfo getFinalParkInfo();

    int getTime();

    int getDelay();

    boolean isDelayed();

    int getCost();

    UserInfo getUserInfo();

    BikeInfo getBikeInfo();


}
