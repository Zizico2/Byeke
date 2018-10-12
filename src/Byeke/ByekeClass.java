package Byeke;


import Byeke.Bike.Bike;
import Byeke.Park.Park;
import Byeke.Park.ParkInfo;
import Byeke.PickUp.PickUp;
import Byeke.PickUp.PickUpInfo;
import Byeke.User.User;
import Byeke.User.UserInfo;
import Exceptions.*;
import dataStructures.Iterator;

import static Byeke.Bike.BikeClass.createBike;
import static Byeke.Park.ParkClass.createPark;
import static Byeke.PickUp.PickUpClass.createPickUp;
import static Byeke.User.UserClass.createUser;

public class ByekeClass implements Byeke {

    private static final int MINIMUM_BALANCE = 5;

    private User user;

    private Bike bike;

    private Park park;


    @Override
    public void addUser(String userId, String tin, String emailAddress, String phoneNumber, String name, String address) throws DuplicateUserIdException {
        if (user == null ||!user.getId().equals(userId)){
            user = createUser(userId,tin,emailAddress,phoneNumber,name,address);
        } else
            throw new DuplicateUserIdException();
    }

    @Override
    public void removeUser(String userId) throws InexistantUserIdException, UserHasPickUpsException {
        if (!user.getId().equals(userId))
            throw new InexistantUserIdException();
        else if (user.hasPickUps())
            throw new UserHasPickUpsException();
        else
            user = null;

    }

    @Override
    public UserInfo getUserInfo(String userId) throws InexistantUserIdException {
        if (!user.getId().equals(userId))
            throw new InexistantUserIdException();
        return user;
    }

    @Override
    public void addPark(String parkId, String name, String address) throws DuplicateParkIdException {
        if (park == null || !park.getId().equals(parkId))
            park = createPark(parkId,name,address);
         else
            throw new DuplicateParkIdException();
    }

    @Override
    public void addBike(String bikeId, String parkId, String plate) throws DuplicateBikeIdException, InexistantParkIdException {
        if (bike != null && !bike.getId().equals(bikeId))
            throw new DuplicateBikeIdException();
        else if (!park.getId().equals(parkId))
            throw new InexistantParkIdException();
        else {
            bike = createBike(bikeId, plate);
            park.addBike(bike);
        }
    }

    @Override
    public void removeBike(String bikeId) throws InexistantBikeIdException, BikeHasPickUpsException {
        if (!bike.getId().equals(bikeId))
            throw new InexistantBikeIdException();
        else if (bike.hasPickUps())
            throw new BikeHasPickUpsException();
        else
            park.removeBike(bike);
    }

    @Override
    public ParkInfo getParkInfo(String parkId) throws InexistantParkIdException {
        if (!park.getId().equals(parkId))
            throw new InexistantParkIdException();
        return park;
    }

    @Override
    public void pickUp(String bikeId, String userId) throws InexistantBikeIdException, BikeOnTheMoveException, InexistantUserIdException, UserOnTheMoveException, LowBalanceException {
        if (!bike.getId().equals(bikeId))
            throw new InexistantBikeIdException();
        if (bike.isMoving())
            throw new BikeOnTheMoveException();
        if (!bike.getId().equals(bikeId))
            throw new InexistantUserIdException();
        if (user.isMoving())
            throw new UserOnTheMoveException();
        if (user.getBalance() < MINIMUM_BALANCE)
            throw new LowBalanceException();

        PickUp pickUp = createPickUp(park, bike, user);
        bike.pickUp(pickUp);
        user.pickUp(pickUp);

    }

    @Override
    public UserInfo PickDown(String bikeId, String parkId, int time) throws InexistantBikeIdException, BikeNotOnTheMoveException, InexistantParkIdException, InvalidTimeException {
        if (!bike.getId().equals(bikeId))
            throw new InexistantBikeIdException();
        if (!bike.isMoving())
            throw new BikeNotOnTheMoveException();
        if (!park.getId().equals(parkId))
            throw new InexistantParkIdException();
        if (time <= 0)
            throw new InvalidTimeException();
        bike.pickDown();
        user.pickDown();
        return user;
    }

    @Override
    public UserInfo ChargeUser(String userId, int value) throws InexistantUserIdException, InvalidValueException {
        if (!user.getId().equals(userId))
            throw new InexistantUserIdException();
        if (value < 0)
            throw new InvalidValueException();
        user.addBalance(value);
        return user;
    }

    @Override
    public Iterator<PickUpInfo> bikePickUps(String bikeId) throws InexistantBikeIdException, BikeHasNoPickUpsException, BikeOnTheMoveException {
        if (!bike.getId().equals(bikeId))
            throw new InexistantBikeIdException();
        if (!bike.hasPickUps())
            throw new BikeHasNoPickUpsException();
        if (bike.isMoving())
            throw new BikeOnFirstPickUpException();
        return bike.getArchivedPickUps();
    }

    @Override
    public Iterator<PickUpInfo> userPickUps(String userId) throws InexistantUserIdException, UserHasNoPickUpsException, UserOnFirstPickUpException {
        if (!user.getId().equals(userId))
            throw new InexistantUserIdException();
        if (!user.hasPickUps())
            throw new UserHasNoPickUpsException();
        if (user.isOnFirstPickup())
            throw new UserOnFirstPickUpException();
        return user.getArchivedPickUps();
    }

    @Override
    public void ParkedBike(String bikeId, String parkId) throws InexistantBikeIdException, InexistantParkIdException, BikeNotParkedException {
        if (!bike.getId().equals(bikeId))
            throw new InexistantBikeIdException();
        if (!park.getId().equals(parkId))
            throw new InexistantParkIdException();
        if (!park.hasParkedBikes())
            throw new BikeNotParkedException();
    }

    @Override
    public UserInfo ListDelayed() throws NoDelaysException {
        if (!user.hasDelays())
            throw new NoDelaysException();
        return user;
    }

    @Override
    public ParkInfo FavouriteParks() throws NoPickUpsException {
        if (!park.hasPickUps())
            throw new NoPickUpsException();
        return park;
    }
}
