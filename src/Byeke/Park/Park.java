package Byeke.Park;

import Byeke.Bike.Bike;

public interface Park extends ParkInfo {

    void addBike(Bike bike);

    void removeBike(Bike bike);
}
