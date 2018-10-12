package Byeke.Park;

import Byeke.Bike.Bike;
import Byeke.PickUp.PickUp;

public interface Park extends ParkInfo {

    void addBike(Bike bike);

    void removeBike();

    void pickUp(PickUp pickup);

    void pickDown(PickUp pickup);
}
