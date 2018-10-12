package Byeke.Bike;
import dataStructures.*;
import Byeke.PickUp.*;

public class BikeClass implements Bike {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private String iD;
    private String plate;
    private List<PickUpInfo> archivedPickUps;
    private PickUp activePickUp;

    public static BikeClass createBike(String iD, String plate){
        return new BikeClass(iD, plate);
    }

    private BikeClass(String iD, String plate) {
        this.iD = iD;
        this.plate = plate;
        archivedPickUps = new DoublyLinkedList<>();
        activePickUp = null;
    }

    public void pickUp(PickUp pickUp){
        activePickUp = pickUp;
    }

    public void pickDown(){
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
