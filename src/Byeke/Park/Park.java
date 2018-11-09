package Byeke.Park;

import Byeke.Bike.Bike;
import Byeke.PickUp.PickUp;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public interface Park extends ParkInfo {

    void addBike(Bike bike);

    void removeBike();

    void pickUp(PickUp pickup);

    void pickDown(PickUp pickup);
}
