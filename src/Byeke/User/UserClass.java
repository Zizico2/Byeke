package Byeke.User;

import Byeke.PickUp.PickUp;
import Byeke.PickUp.PickUpInfo;
import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;

public class UserClass implements User {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final int INITIAL_BALANCE = 5;

    private String iD;
    private String name;
    private String address;
    private String emailAdress;
    private String phoneNumber;
    private String tin;
    private int balance;
    private int points;

    private List<PickUpInfo> archivedPickUps;
    private PickUp activePickUp;

    public static UserClass createUser(String iD, String tin, String emailAddress, String phoneNumber, String name, String address) {
        return new UserClass(iD, name, address, emailAddress, phoneNumber, tin);
    }


    private UserClass(String iD, String name, String address, String emailAddress, String phoneNumber, String tin) {
        this.iD = iD;
        this.name = name;
        this.address = address;
        this.emailAdress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.tin = tin;
        this.balance = INITIAL_BALANCE;
        this.points = 0;
        this.activePickUp = null;
        this.archivedPickUps = new DoublyLinkedList<>();
    }

    @Override
    public String getId() {
        return iD;
    }

    public void pickUp(PickUp pickUp) {
        activePickUp = pickUp;
    }

    @Override
    public void pickDown() {
        archivedPickUps.addLast(activePickUp);
        activePickUp = null;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmailAddress() {
        return emailAdress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTin() {
        return tin;
    }

    public int getBalance() {
        return balance;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public boolean hasDelays() {
        return points != 0;
    }

    @Override
    public boolean hasPickUps() {
        return !archivedPickUps.isEmpty() || activePickUp != null;
    }

    public boolean isOnFirstPickup() {
        return archivedPickUps.isEmpty() && activePickUp != null;
    }

    @Override
    public Iterator<PickUpInfo> getArchivedPickUps() {
        return archivedPickUps.iterator();
    }

    @Override
    public boolean isMoving() {
        return activePickUp != null;
    }

    public void addBalance(int balance) {
        this.balance += balance;
    }
}