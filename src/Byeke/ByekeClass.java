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
import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;
import dataStructures.Iterator;

import static Byeke.Bike.BikeClass.createBike;
import static Byeke.Park.ParkClass.createPark;
import static Byeke.User.UserClass.createUser;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public class ByekeClass implements Byeke {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final int MINIMUM_BALANCE = 5;

    private Dictionary<String, User> users;

    private Dictionary<String, Bike> bikes;

    private Park park;

    private Dictionary<String, PickUp> activePickUps;

    public ByekeClass() {
        this.users = new ChainedHashTable<>();
        this.bikes = new ChainedHashTable<>();
        this.park = null;
        this.activePickUps = new ChainedHashTable<>();
    }

    @Override
    public void addUser(String userId, String tin, String emailAddress, String phoneNumber, String name, String address) throws DuplicateUserIdException {
        if (users.find(userId) != null)
            throw new DuplicateUserIdException();
        users.insert(userId, createUser(userId, tin, emailAddress, phoneNumber, name, address));
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
        if (users.find(userId) == null) {
            throw new InexistantUserIdException();
        }
        return users.find(userId);
    }

    @Override
    public void addPark(String parkId, String name, String address) throws DuplicateParkIdException {
        if (park == null || !park.getId().equalsIgnoreCase(parkId))
            park = createPark(parkId, name, address);
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

    @Override
    public void removeBike(String bikeId) throws InexistantBikeIdException, BikeHasPickUpsException {
        if (bikes.find(bikeId) == null)
            throw new InexistantBikeIdException();
        if (bikes.find(bikeId).hasPickUps())
            throw new BikeHasPickUpsException();
        bikes.remove(bikeId);
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
        if (bikes.find(bikeId) == null)
            throw new InexistantBikeIdException();
        if (bikes.find(bikeId).isMoving())
            throw new BikeOnTheMoveException();
        if (users.find(userId) == null)
            throw new InexistantUserIdException();
        if (users.find(userId).isMoving())
            throw new UserOnTheMoveException();
        if (users.find(userId).getBalance() < MINIMUM_BALANCE)
            throw new LowBalanceException();

        PickUp pickUp = PickUpClass.createPickUp(park, bikes.find(bikeId), users.find(userId));
        activePickUps.insert(bikeId, pickUp);
        bikes.find(bikeId).pickUp(pickUp);
        users.find(userId).pickUp(pickUp);
        park.pickUp(pickUp);
    }

    @Override
    public UserInfo pickDown(String bikeId, String parkId, int time) throws InexistantBikeIdException, BikeNotOnTheMoveException, InexistantParkIdException, InvalidTimeException {
        if (bikes.find(bikeId) == null)
            throw new InexistantBikeIdException();
        if (!bikes.find(bikeId).isMoving())
            throw new BikeNotOnTheMoveException();
        if (park == null || !park.getId().equalsIgnoreCase(parkId))
            throw new InexistantParkIdException();
        if (time <= 0)
            throw new InvalidTimeException();
        PickUp pickUp = activePickUps.find(bikeId);
        User user = pickUp.getUser();
        pickUp.pickDown(park, time);
        park.pickDown(pickUp);
        bikes.find(bikeId).pickDown();
        user.pickDown();
        activePickUps.remove(bikeId);

        return user;
    }

    @Override
    public UserInfo ChargeUser(String userId, int value) throws InexistantUserIdException, InvalidValueException {
        if (users.find(userId) == null)
            throw new InexistantUserIdException();
        if (value <= 0)
            throw new InvalidValueException();
        users.find(userId).addBalance(value);
        return users.find(userId);
    }

    @Override
    public Iterator<PickUpInfo> bikePickUps(String bikeId) throws InexistantBikeIdException, BikeHasNoPickUpsException, BikeOnTheMoveException {
        if (bikes.find(bikeId) == null)
            throw new InexistantBikeIdException();
        if (!bikes.find(bikeId).hasPickUps())
            throw new BikeHasNoPickUpsException();
        if (bikes.find(bikeId).isOnFirstPickUp())
            throw new BikeOnFirstPickUpException();
        return bikes.find(bikeId).getArchivedPickUps();
    }

    @Override
    public Iterator<PickUpInfo> userPickUps(String userId) throws InexistantUserIdException, UserHasNoPickUpsException, UserOnFirstPickUpException {
        if (users.find(userId) == null)
            throw new InexistantUserIdException();
        if (!users.find(userId).hasPickUps())
            throw new UserHasNoPickUpsException();
        if (users.find(userId).isOnFirstPickup())
            throw new UserOnFirstPickUpException();
        return users.find(userId).getArchivedPickUps();
    }

    @Override
    public void parkedBike(String bikeId, String parkId) throws InexistantBikeIdException, InexistantParkIdException, BikeNotParkedException {
        if (bikes.find(bikeId) == null)
            throw new InexistantBikeIdException();
        if (park == null || !park.getId().equalsIgnoreCase(parkId))
            throw new InexistantParkIdException();
        if (!park.hasParkedBikes())
            throw new BikeNotParkedException();
    }

    @Override
    public UserInfo listDelayed() throws NoDelaysException {
        //if (!users.find(user).hasDelays())
        throw new NoDelaysException();
        //return users.find(user);
    }

    @Override
    public ParkInfo favouriteParks() throws NoPickUpsException {
        if (!park.hasPickUps())
            throw new NoPickUpsException();
        return park;
    }
}
