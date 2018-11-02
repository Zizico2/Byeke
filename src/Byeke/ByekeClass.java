package Byeke;


import Byeke.Bike.Bike;
import Byeke.Park.Park;
import Byeke.Park.ParkInfo;
import Byeke.PickUp.PickUp;
import Byeke.PickUp.PickUpClass;
import Byeke.PickUp.PickUpInfo;
import Byeke.User.User;
import Byeke.User.UserInfo;
import Exceptions.*;
import dataStructures.*;

import static Byeke.Bike.BikeClass.createBike;
import static Byeke.Park.ParkClass.createPark;
import static Byeke.User.UserClass.createUser;

public class ByekeClass implements Byeke {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final int MINIMUM_BALANCE = 5;

    private Dictionary<String, User> users;

    private Dictionary<String, Bike> bikes;

    private Park park;

    private List<PickUp> activePickUps;

    public ByekeClass() {
        this.users = new ChainedHashTable<>();
        this.bikes = new ChainedHashTable<>();
        this.park = null;
        this.activePickUps = new DoublyLinkedList<>();
    }

    @Override
    public void addUser(String userId, String tin, String emailAddress, String phoneNumber, String name, String address) throws DuplicateUserIdException {
        if (users.find(userId) != null)
            throw new DuplicateUserIdException();
        users.insert(userId, createUser(userId,tin,emailAddress,phoneNumber,name,address));
    }

    @Override
    public void removeUser(String userId) throws InexistantUserIdException, UserHasPickUpsException {
        if (users.find(userId) == null)
            throw new InexistantUserIdException();
        if (users.find(userId).hasPickUps())
            throw new UserHasPickUpsException();
        users.remove(userId);

    }

    @Override
    public UserInfo getUserInfo(String userId) throws InexistantUserIdException {
        if (user == null || !user.getId().equalsIgnoreCase(userId)) {
            throw new InexistantUserIdException();
        }
        return user;
    }

    @Override
    public void addPark(String parkId, String name, String address) throws DuplicateParkIdException {
        if (park == null || !park.getId().equalsIgnoreCase(parkId))
            park = createPark(parkId,name,address);
         else
            throw new DuplicateParkIdException();
    }

    @Override
    public void addBike(String bikeId, String parkId, String plate) throws DuplicateBikeIdException, InexistantParkIdException {
        if (bikes.find(bikeId) != null)
            throw new DuplicateBikeIdException();
        if (!park.getId().equalsIgnoreCase(parkId))
            throw new InexistantParkIdException();
        bikes.insert(bikeId, createBike(bikeId, plate));
        park.addBike(createBike(bikeId, plate));
        }
    }

    @Override
    public void removeBike(String bikeId) throws InexistantBikeIdException, BikeHasPickUpsException {
        if (bike == null || !bike.getId().equalsIgnoreCase(bikeId))
            throw new InexistantBikeIdException();
        else if (bike.hasPickUps())
            throw new BikeHasPickUpsException();
        else
            bike = null;
            park.removeBike();

    }

    @Override
    public ParkInfo getParkInfo(String parkId) throws InexistantParkIdException {
        if (park == null || !park.getId().equalsIgnoreCase(parkId))
            throw new InexistantParkIdException();
        return park;
    }

    @Override
    public void pickUp(String bikeId, String userId) throws InexistantBikeIdException, BikeOnTheMoveException, InexistantUserIdException, UserOnTheMoveException, LowBalanceException {
        if (bike == null || !bike.getId().equalsIgnoreCase(bikeId))
            throw new InexistantBikeIdException();
        if (bike.isMoving())
            throw new BikeOnTheMoveException();
        if (user == null || !user.getId().equalsIgnoreCase(userId))
            throw new InexistantUserIdException();
        if (user.isMoving())
            throw new UserOnTheMoveException();
        if (user.getBalance() < MINIMUM_BALANCE)
            throw new LowBalanceException();

        activePickUp = PickUpClass.createPickUp(park, bike, user);
        bike.pickUp(activePickUp);
        user.pickUp(activePickUp);
        park.pickUp(activePickUp);
    }

    @Override
    public UserInfo pickDown(String bikeId, String parkId, int time) throws InexistantBikeIdException, BikeNotOnTheMoveException, InexistantParkIdException, InvalidTimeException {
        if (bike == null || !bike.getId().equalsIgnoreCase(bikeId))
            throw new InexistantBikeIdException();
        if (!bike.isMoving())
            throw new BikeNotOnTheMoveException();
        if (park == null || !park.getId().equalsIgnoreCase(parkId))
            throw new InexistantParkIdException();
        if (time <= 0)
            throw new InvalidTimeException();
        activePickUp.pickDown(park, time);
        bike.pickDown();
        user.pickDown();
        park.pickDown(activePickUp);
        activePickUp = null;

        return user;
    }

    @Override
    public UserInfo ChargeUser(String userId, int value) throws InexistantUserIdException, InvalidValueException {
        if (user == null || !user.getId().equalsIgnoreCase(userId))
            throw new InexistantUserIdException();
        if (value <= 0)
            throw new InvalidValueException();
        user.addBalance(value);
        return user;
    }

    @Override
    public Iterator<PickUpInfo> bikePickUps(String bikeId) throws InexistantBikeIdException, BikeHasNoPickUpsException, BikeOnTheMoveException {
        if (bike == null || !bike.getId().equalsIgnoreCase(bikeId))
            throw new InexistantBikeIdException();
        if (!bike.hasPickUps())
            throw new BikeHasNoPickUpsException();
        if (bike.isOnFirstPickUp())
            throw new BikeOnFirstPickUpException();
        return bike.getArchivedPickUps();
    }

    @Override
    public Iterator<PickUpInfo> userPickUps(String userId) throws InexistantUserIdException, UserHasNoPickUpsException, UserOnFirstPickUpException {
        if (user == null || !user.getId().equalsIgnoreCase(userId))
            throw new InexistantUserIdException();
        if (!user.hasPickUps())
            throw new UserHasNoPickUpsException();
        if (user.isOnFirstPickup())
            throw new UserOnFirstPickUpException();
        return user.getArchivedPickUps();
    }

    @Override
    public void parkedBike(String bikeId, String parkId) throws InexistantBikeIdException, InexistantParkIdException, BikeNotParkedException {
        if (bike == null || !bike.getId().equalsIgnoreCase(bikeId))
            throw new InexistantBikeIdException();
        if (park == null || !park.getId().equalsIgnoreCase(parkId))
            throw new InexistantParkIdException();
        if (!park.hasParkedBikes())
            throw new BikeNotParkedException();
    }

    @Override
    public UserInfo listDelayed() throws NoDelaysException {
        if (!user.hasDelays())
            throw new NoDelaysException();
        return user;
    }

    @Override
    public ParkInfo favouriteParks() throws NoPickUpsException {
        if (!park.hasPickUps())
            throw new NoPickUpsException();
        return park;
    }
}
