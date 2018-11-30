package Byeke.Park;

import Byeke.Bike.Bike;
import Byeke.Bike.BikeInfo;
import Byeke.PickUp.PickUp;
import Byeke.PickUp.PickUpInfo;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public interface Park extends ParkInfo {

    void addBike(Bike bike);

    void removeBike(String bikeId);

    void pickUp(PickUpInfo pickUpInfo);

    void pickDown(PickUpInfo pickup, Bike bike);
}
