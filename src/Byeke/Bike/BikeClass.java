package Byeke.Bike;

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
    private PickUp activePickUp;

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
        activePickUp = null;
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

    public void pickUp(PickUp pickUp) {
        activePickUp = pickUp;
    }

    public void pickDown() {
        archivedPickUps.addLast(activePickUp);
        activePickUp = null;
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
        return activePickUp != null;
    }

    @Override
    public boolean hasPickUps() {
        return !archivedPickUps.isEmpty() || activePickUp != null;
    }

    @Override
    public boolean isOnFirstPickUp() {
        return activePickUp != null && archivedPickUps.isEmpty();
    }
}
