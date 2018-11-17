package Byeke.User;

import Byeke.PickUp.PickUp;
import Byeke.PickUp.PickUpInfo;
import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;

public class UserClass implements User {

    //CONSTANTS
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * Initial balance of a User
     */
    private static final int INITIAL_BALANCE = 5;

    //INSTANCE VARIABLES
    /**
     * Unique identifier
     */
    private final String iD;

    /**
     * Taxpayer Identification Number
     */
    private final String tin;

    /**
     * Email Address
     */
    private final String emailAddress;

    /**
     * Phone Number
     */
    private final String phoneNumber;

    /**
     * Name
     */
    private final String name;

    /**
     * Address
     */
    private final String address;

    /**
     * Current balance (used to pay for PickUps), affected by pickDown() and addBalance(), read by getBalance()
     */
    private int balance;

    /**
     * Current points, (gained by pickDown()) affected by pickDown(), read by getPoints()
     */
    private int points;

    /**
     * PickUps that have ended (ie. after pickDown())
     */
    private List<PickUpInfo> archivedPickUps;
    private PickUp activePickUp;

    //FACTORY METHODS
    //CONSTRUCTOR
    private UserClass(String iD, String tin, String emailAddress, String phoneNumber, String name, String address) {
        this.iD = iD;
        this.name = name;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.tin = tin;
        this.balance = INITIAL_BALANCE;
        this.points = 0;
        this.activePickUp = null;
        this.archivedPickUps = new DoublyLinkedList<>();
    }

    /**
     * *Constructor replacement*
     *
     * @param iD           Unique identifier
     * @param tin          Taxpayer Identification Number
     * @param emailAddress Email address
     * @param phoneNumber  Phone number
     * @param name         Name
     * @param address      Address
     * @return Default User
     */
    public static User createUser(String iD, String tin, String emailAddress, String phoneNumber, String name, String address) {
        return new UserClass(iD, tin, emailAddress, phoneNumber, name, address);
    }

    //PUBLIC METHODS

    /**
     * @return the iD of a User
     */
    @Override
    public String getId() {
        return iD;
    }

    @Override
    public void pickUp(PickUp pickUp) {
        activePickUp = pickUp;
    }

    @Override
    public void pickDown() {
        archivedPickUps.addLast(activePickUp);
        activePickUp = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getTin() {
        return tin;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
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

    @Override
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

    @Override
    public void addBalance(int balance) {
        this.balance += balance;
    }
}