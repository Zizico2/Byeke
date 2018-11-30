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

/*
*
*  Apenas AVLTreeNode, AVLTree, BTSKeyOrderIterator e
*
*
*
*
*
*
*
*/
public class ByekeClass implements Byeke {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final int MINIMUM_BALANCE = 5;

    private Dictionary<String, User> users;

    private OrderedDictionary<Integer, DoublyLinkedList<UserInfo>> delayedUsers;

    private OrderedDictionary<String, ParkInfo> favouriteParks;

    private Dictionary<String, Bike> bikes;

    private Dictionary<String, Park> parks;

    private Dictionary<String, PickUp> activePickUps;

    public ByekeClass() {
        this.users = new ChainedHashTable<>();
        this.bikes = new ChainedHashTable<>();
        this.parks = new ChainedHashTable<>();
        this.activePickUps = new ChainedHashTable<>();
        this.delayedUsers = new AVLTree<>();
        this.favouriteParks = new AVLTree<>();
    }

    @Override
    public void addUser(String userId, String tin, String emailAddress, String phoneNumber, String name, String address) throws DuplicateUserIdException {
        if (users.find(userId.toLowerCase()) != null)
            throw new DuplicateUserIdException();
        users.insert(userId.toLowerCase(), createUser(userId, tin, emailAddress, phoneNumber, name, address));
    }

    @Override
    public void removeUser(String userId) throws InexistantUserIdException, UserHasPickUpsException {
        if (users.find(userId.toLowerCase()) == null)
            throw new InexistantUserIdException();
        if (users.find(userId.toLowerCase()).hasPickUps())
            throw new UserHasPickUpsException();
        users.remove(userId.toLowerCase());

    }

    @Override
    public UserInfo getUserInfo(String userId) throws InexistantUserIdException {
        if (users.find(userId.toLowerCase()) == null) {
            throw new InexistantUserIdException();
        }
        return users.find(userId.toLowerCase());
    }

    @Override
    public void addPark(String parkId, String name, String address) throws DuplicateParkIdException {
        if (parks.find(parkId.toLowerCase()) != null)
            throw new DuplicateParkIdException();
        parks.insert(parkId.toLowerCase(), createPark(parkId, name, address));

    }

    @Override
    public void addBike(String bikeId, String parkId, String plate) throws DuplicateBikeIdException, InexistantParkIdException {
        Park park = parks.find(parkId.toLowerCase());
        if (bikes.find(bikeId.toLowerCase()) != null)
            throw new DuplicateBikeIdException();
        if (park == null)
            throw new InexistantParkIdException();
        Bike bike = createBike(bikeId, plate, park);
        bikes.insert(bikeId.toLowerCase(), bike);
        park.addBike(bike);
    }

    @Override
    public void removeBike(String bikeId) throws InexistantBikeIdException, BikeHasPickUpsException {
        if (bikes.find(bikeId.toLowerCase()) == null)
            throw new InexistantBikeIdException();
        if (bikes.find(bikeId.toLowerCase()).hasPickUps())
            throw new BikeHasPickUpsException();

        Bike bike = bikes.remove(bikeId.toLowerCase());
        String parkId = bike.getCurrentPark().getId();
        parks.find(parkId.toLowerCase()).removeBike(bikeId.toLowerCase());
    }

    @Override
    public ParkInfo getParkInfo(String parkId) throws InexistantParkIdException {   
        Park park = parks.find(parkId.toLowerCase());
        if (park == null)
            throw new InexistantParkIdException();
        return park;
    }

    @Override
    public void pickUp(String bikeId, String userId) throws InexistantBikeIdException, BikeOnTheMoveException, InexistantUserIdException, UserOnTheMoveException, LowBalanceException {
        Bike bike = bikes.find(bikeId.toLowerCase());
        User user = users.find(userId.toLowerCase());

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

        Park park = parks.find(bike.getCurrentPark().getId().toLowerCase());

        PickUp pickUp = PickUpClass.createPickUp(park, bikes.find(bikeId.toLowerCase()), users.find(userId.toLowerCase()));

        activePickUps.insert(bikeId.toLowerCase(), pickUp);
        bike.pickUp(pickUp);
        user.pickUp(pickUp);
        park.pickUp(pickUp);
    }

    @Override
    public UserInfo pickDown(String bikeId, String parkId, int time) throws InexistantBikeIdException, BikeNotOnTheMoveException, InexistantParkIdException, InvalidTimeException {
        Bike bike = bikes.find(bikeId.toLowerCase());
        Park park = parks.find(parkId.toLowerCase());

        if (bike == null)
            throw new InexistantBikeIdException();
        if (!bike.isMoving())
            throw new BikeNotOnTheMoveException();
        if (park == null || !park.getId().equalsIgnoreCase(parkId))
            throw new InexistantParkIdException();
        if (time <= 0)
            throw new InvalidTimeException();

        PickUp pickUp = activePickUps.find(bikeId.toLowerCase());

        User user = users.find(pickUp.getUserInfo().getId().toLowerCase());

        pickUp.pickDown(park, time);
        park.pickDown(pickUp, bike);
        bike.pickDown();
        activePickUps.remove(bikeId.toLowerCase());

        if (pickUp.isDelayed())
            UpdateDelayedUsers(user);
        else
            user.pickDown();

        UpdateFavouriteParks(park);

        return user;
    }

    private void UpdateFavouriteParks(Park park) {
        if (favouriteParks.isEmpty() || park.getNoPickUps() >= favouriteParks.iterator().next().getValue().getNoPickUps())
            favouriteParks.insert(park.getName().toLowerCase(), park);
    }

    private void UpdateDelayedUsers(User user) {
            int oldPoints = user.getPoints();
            DoublyLinkedList<UserInfo> oldList = delayedUsers.find(oldPoints);
            user.pickDown();
            oldList.remove(user);
            if (oldList.isEmpty())
                delayedUsers.remove(oldPoints);

            int points = user.getPoints();
            if (delayedUsers.find(points) == null) {
                delayedUsers.insert(points, new DoublyLinkedList<>());
                delayedUsers.find(points).addLast(user);
            }
        }

    @Override
    public UserInfo ChargeUser(String userId, int value) throws InexistantUserIdException, InvalidValueException {
        if (users.find(userId.toLowerCase()) == null)
            throw new InexistantUserIdException();
        if (value <= 0)
            throw new InvalidValueException();
        users.find(userId.toLowerCase()).addBalance(value);
        return users.find(userId.toLowerCase());
    }

    @Override
    public Iterator<PickUpInfo> bikePickUps(String bikeId) throws InexistantBikeIdException, BikeHasNoPickUpsException, BikeOnTheMoveException {
        if (bikes.find(bikeId.toLowerCase()) == null)
            throw new InexistantBikeIdException();
        if (!bikes.find(bikeId.toLowerCase()).hasPickUps())
            throw new BikeHasNoPickUpsException();
        if (bikes.find(bikeId.toLowerCase()).isOnFirstPickUp())
            throw new BikeOnFirstPickUpException();
        return bikes.find(bikeId.toLowerCase()).getArchivedPickUps();
    }

    @Override
    public Iterator<PickUpInfo> userPickUps(String userId) throws InexistantUserIdException, UserHasNoPickUpsException, UserOnFirstPickUpException {
        if (users.find(userId.toLowerCase()) == null)
            throw new InexistantUserIdException();
        if (!users.find(userId.toLowerCase()).hasPickUps())
            throw new UserHasNoPickUpsException();
        if (users.find(userId.toLowerCase()).isOnFirstPickup())
            throw new UserOnFirstPickUpException();
        return users.find(userId.toLowerCase()).getArchivedPickUps();
    }

    @Override
    public void parkedBike(String bikeId, String parkId) throws InexistantBikeIdException, InexistantParkIdException, BikeNotParkedException {
        Park park = parks.find(parkId.toLowerCase());

        if (bikes.find(bikeId.toLowerCase()) == null)
            throw new InexistantBikeIdException();
        if (park == null)
            throw new InexistantParkIdException();
        if (bikes.find(bikeId.toLowerCase()).getCurrentPark() == null || !bikes.find(bikeId.toLowerCase()).getCurrentPark().getId().equalsIgnoreCase(parkId))
            throw new BikeNotParkedException();
    }

    @Override
    public Iterator<Entry<Integer, DoublyLinkedList<UserInfo>>> listDelayed() throws NoDelaysException {
        if (delayedUsers.isEmpty())
            throw new NoDelaysException();
        return delayedUsers.iterator();
    }

    @Override
    public Iterator<Entry<String, ParkInfo>> favouriteParks() throws NoPickUpsException {
        if (favouriteParks.isEmpty())
            throw new NoPickUpsException();
        return favouriteParks.iterator();
    }
}
