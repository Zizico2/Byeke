package Byeke.PickUp;

import Byeke.Bike.Bike;
import Byeke.Bike.BikeInfo;
import Byeke.Park.Park;
import Byeke.Park.ParkInfo;
import Byeke.User.User;
import Byeke.User.UserInfo;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public interface PickUp extends PickUpInfo {

    void pickDown(ParkInfo finalPark, int time);
}
