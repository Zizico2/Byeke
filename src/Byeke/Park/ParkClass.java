package Byeke.Park;

import Byeke.Bike.Bike;
import Byeke.PickUp.PickUp;
import Byeke.PickUp.PickUpInfo;
import dataStructures.DoublyLinkedList;
import dataStructures.List;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public class ParkClass implements Park {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private String iD;

    private String address;

    private String name;

    private Bike parkedBike;

    private List<PickUpInfo> pickUps;


    public static ParkClass createPark(String iD, String name, String address) {
        return new ParkClass(iD, name, address);
    }

    private ParkClass(String iD, String name, String address) {
        this.iD = iD;
        this.name = name;
        this.address = address;
        this.parkedBike = null;
        this.pickUps = new DoublyLinkedList<>();
    }


    @Override
    public void addBike(Bike bike) {
        parkedBike = bike;
    }

    @Override
    public void removeBike() {
        parkedBike = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNoPickUps() {
        return pickUps.size();
    }

    @Override
    public boolean hasPickUps() {
        return !pickUps.isEmpty();
    }

    @Override
    public int getNoParkedBikes() {
        return parkedBike != null ? 1 : 0;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public boolean hasParkedBikes() {
        return parkedBike != null;
    }

    @Override
    public String getId() {
        return iD;
    }

    public void pickUp(PickUp pickup) {
        parkedBike = null;
        pickUps.addLast(pickup);
    }

    @Override
    public void pickDown(PickUp pickup) {
        parkedBike = pickup.getBike();
    }


}
