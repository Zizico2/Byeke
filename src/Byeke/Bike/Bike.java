package Byeke.Bike;

import Byeke.PickUp.PickUp;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public interface Bike extends BikeInfo {
    /**
     * @param pickUp
     */
    void pickUp(PickUp pickUp);

    void pickDown();

}
