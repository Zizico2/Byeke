package Byeke.PickUp;

import Byeke.Bike.Bike;
import Byeke.Park.Park;
import Byeke.User.User;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public interface PickUp extends PickUpInfo {

    void pickDown(Park finalPark, int time);

    Bike getBike();

    User getUser();
}
