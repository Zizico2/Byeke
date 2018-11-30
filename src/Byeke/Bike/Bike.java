package Byeke.Bike;

import Byeke.PickUp.PickUp;
import Byeke.PickUp.PickUpInfo;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public interface Bike extends BikeInfo {
    /**
     * @param pickUpInfo
     */
    void pickUp(PickUpInfo pickUpInfo);

    void pickDown();

}
