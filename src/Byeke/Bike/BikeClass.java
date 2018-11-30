package Byeke.Bike;

import Byeke.Park.Park;
import Byeke.Park.ParkInfo;
import Byeke.PickUp.PickUp;
import Byeke.PickUp.PickUpInfo;
import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public class BikeClass implements Bike {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * Instance variables
     */
    private String iD;
    private String plate;
    private List<PickUpInfo> archivedPickUps;
    private PickUpInfo currentPickUp;
    private ParkInfo currentPark;

    /**
     * Constructor
     *
     * @param iD    unique identifier
     * @param plate license plate
     */
    private BikeClass(String iD, String plate) {
        this.iD = iD;
        this.plate = plate;
        archivedPickUps = new DoublyLinkedList<>();
        currentPickUp = null;
    }

    /**
     * Factory method
     *
     * @param iD    unique identifier
     * @param plate license plate
     * @return newly created Bike
     */
    public static BikeClass createBike(String iD, String plate) {
        return new BikeClass(iD, plate);
    }

    public void pickUp(PickUpInfo pickUpInfo) {
        currentPickUp = pickUpInfo;
        currentPark = null;
    }

    public void pickDown() {
        archivedPickUps.addLast(currentPickUp);
        currentPark = currentPickUp.getFinalParkInfo();
        currentPickUp = null;

    }

    public String getId() {
        return iD;
    }

    public String getPlate() {
        return plate;
    }

    public Iterator<PickUpInfo> getArchivedPickUps() {
        return archivedPickUps.iterator();
    }

    public boolean isMoving() {
        return currentPickUp != null;
    }

    @Override
    public boolean hasPickUps() {
        return !archivedPickUps.isEmpty() || currentPickUp != null;
    }

    @Override
    public boolean isOnFirstPickUp() {
        return currentPickUp != null && archivedPickUps.isEmpty();
    }

    public PickUpInfo getLastPickUp(){
        if (currentPickUp == null)
            return archivedPickUps.getLast();
        return currentPickUp;
    }

    public ParkInfo getCurrentPark() {
        return currentPark;
    }
}
