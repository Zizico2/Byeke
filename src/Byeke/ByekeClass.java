package Byeke;


import Byeke.Bike.Bike;
import Byeke.Bike.BikeInfo;
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

    private AVLTree<Integer, UserInfo> delayedUsers;

    private Dictionary<String, Bike> bikes;

    private Dictionary<String, Park> parks;

    private Dictionary<String, PickUp> activePickUps;

    public ByekeClass() {
        this.users = new ChainedHashTable<>();
        this.bikes = new ChainedHashTable<>();
        this.parks = new ChainedHashTable<>();
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
        if (parks.find(parkId) != null)
            throw new DuplicateParkIdException();
        parks.insert(parkId, createPark(parkId, name, address));

    }

    @Override
    public void addBike(String bikeId, String parkId, String plate) throws DuplicateBikeIdException, InexistantParkIdException {
        Park park = parks.find(parkId);
        if (bikes.find(bikeId) != null)
            throw new DuplicateBikeIdException();
        if (park == null)
            throw new InexistantParkIdException();
        Bike bike = createBike(bikeId, plate);
        bikes.insert(bikeId, bike);
        park.addBike(bike);
    }

    @Override
    public void removeBike(String bikeId) throws InexistantBikeIdException, BikeHasPickUpsException {
        if (bikes.find(bikeId) == null)
            throw new InexistantBikeIdException();
        if (bikes.find(bikeId).hasPickUps())
            throw new BikeHasPickUpsException();

        String parkId = (bikes.remove(bikeId).getCurrentPark().getId());
        parks.find(parkId).removeBike(bikeId);
    }

    @Override
    public ParkInfo getParkInfo(String parkId) throws InexistantParkIdException {   
        Park park = parks.find(parkId);
        if (park == null)
            throw new InexistantParkIdException();
        return park;
    }

    @Override
    public void pickUp(String bikeId, String userId) throws InexistantBikeIdException, BikeOnTheMoveException, InexistantUserIdException, UserOnTheMoveException, LowBalanceException {
        Bike bike = bikes.find(bikeId);
        User user = users.find(userId);

        if (bike == null)
            throw new InexistantBikeIdException();
        if (bike.isMoving())
            throw new BikeOnTheMoveException();
        if (user == null)
            throw new InexistantUserIdException();
        if (user.isMoving())
            throw new UserOnTheMoveException();
        if (user.getBalance() < MINIMUM_BALANCE)
            throw new LowBalanceException();

        Park park = parks.find(bike.getCurrentPark().getId());

        PickUp pickUp = PickUpClass.createPickUp(park, bikes.find(bikeId), users.find(userId));

        activePickUps.insert(bikeId, pickUp);
        bike.pickUp(pickUp);
        user.pickUp(pickUp);
        park.pickUp(pickUp);
    }

    @Override
    public UserInfo pickDown(String bikeId, String parkId, int time) throws InexistantBikeIdException, BikeNotOnTheMoveException, InexistantParkIdException, InvalidTimeException {
        Bike bike = bikes.find(bikeId);
        Park park = parks.find(parkId);

        if (bike == null)
            throw new InexistantBikeIdException();
        if (!bike.isMoving())
            throw new BikeNotOnTheMoveException();
        if (park == null || !park.getId().equalsIgnoreCase(parkId))
            throw new InexistantParkIdException();
        if (time <= 0)
            throw new InvalidTimeException();

        PickUp pickUp = activePickUps.find(bikeId);

        User user = users.find(pickUp.getUserInfo().getId());

        pickUp.pickDown(park, time);
        park.pickDown(pickUp, bike);
        bike.pickDown();
        user.pickDown();
        activePickUps.remove(bikeId);

        if(pickUp.isDelayed()) {


        }

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
        Park park = parks.find(parkId);

        if (bikes.find(bikeId) == null)
            throw new InexistantBikeIdException();
        if (park == null)
            throw new InexistantParkIdException();
        if (!park.hasParkedBikes())
            throw new BikeNotParkedException();
    }

    @Override
    public Iterator<UserInfo> listDelayed() throws NoDelaysException {
        if (delayedUsers.isEmpty())
            throw new NoDelaysException();
        return delayedUsers.iterator();
    }

    @Override
    public Iterator<ParkInfo> favouriteParks() throws NoPickUpsException {
        if (!park.hasPickUps())
            throw new NoPickUpsException();
        return park;
    }
}
