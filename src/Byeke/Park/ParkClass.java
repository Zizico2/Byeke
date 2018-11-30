package Byeke.Park;

import Byeke.Bike.Bike;
import Byeke.Bike.BikeInfo;
import Byeke.PickUp.PickUpInfo;
import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;
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

    private Dictionary<String, Bike> parkedBikes;

    private List<PickUpInfo> archivedPickUps;


    public static ParkClass createPark(String iD, String name, String address) {
        return new ParkClass(iD, name, address);
    }

    private ParkClass(String iD, String name, String address) {
        this.iD = iD;
        this.name = name;
        this.address = address;
        this.parkedBikes = new ChainedHashTable<>();
        this.archivedPickUps = new DoublyLinkedList<>();
    }


    @Override
    public void addBike(Bike bike){
        parkedBikes.insert(bike.getId(), bike);
    }

    @Override
    public void removeBike(String bikeId) {
        parkedBikes.remove(bikeId);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNoPickUps() {
        return archivedPickUps.size();
    }

    @Override
    public boolean hasPickUps() {
        return !archivedPickUps.isEmpty();
    }

    @Override
    public int getNoParkedBikes() {
        return parkedBikes.size();
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public boolean hasParkedBikes() {
        return !parkedBikes.isEmpty();
    }

    @Override
    public String getId() {
        return iD;
    }

    private void registerPickUp(PickUpInfo pickUpInfo) {
        archivedPickUps.addLast(pickUpInfo);
    }

    @Override
    public void pickDown(PickUpInfo pickUpInfo, Bike bike) {
        addBike(bike);
        registerPickUp(pickUpInfo);
    }

    @Override
    public void pickUp(PickUpInfo pickUpInfo) {
        removeBike(pickUpInfo.getBikeInfo().getId());
        registerPickUp(pickUpInfo);
    }
}
