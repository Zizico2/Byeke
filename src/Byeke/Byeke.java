package Byeke;

import Byeke.Park.ParkInfo;
import Byeke.PickUp.PickUpInfo;
import Byeke.User.UserInfo;
import Exceptions.*;
import dataStructures.DoublyLinkedList;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public interface Byeke extends java.io.Serializable {


    void addUser(String userId, String tin, String emailAddress, String phoneNumber, String name, String address) throws DuplicateUserIdException;

    void removeUser(String userId) throws InexistantUserIdException, UserHasPickUpsException;

    UserInfo getUserInfo(String userId) throws InexistantUserIdException;

    void addPark(String parkId, String name, String address) throws DuplicateParkIdException;

    void addBike(String bikeId, String parkId, String plate) throws DuplicateBikeIdException, InexistantParkIdException;

    void removeBike(String bikeId) throws InexistantBikeIdException, BikeHasPickUpsException;

    ParkInfo getParkInfo(String parkId) throws InexistantParkIdException;

    void pickUp(String bikeId, String userId) throws InexistantBikeIdException, InexistantUserIdException, BikeOnTheMoveException, UserOnTheMoveException, LowBalanceException;

    UserInfo pickDown(String bikeId, String parkId, int time) throws InexistantBikeIdException, InexistantParkIdException, BikeNotOnTheMoveException, InvalidTimeException;

    UserInfo ChargeUser(String userId, int value) throws InexistantUserIdException, InvalidValueException;

    Iterator<PickUpInfo> bikePickUps(String bikeId) throws InexistantBikeIdException, BikeHasNoPickUpsException, BikeOnFirstPickUpException;

    Iterator<PickUpInfo> userPickUps(String userId) throws InexistantUserIdException, UserHasNoPickUpsException, UserOnFirstPickUpException;

    void parkedBike(String bikeId, String parkId) throws InexistantBikeIdException, InexistantParkIdException, BikeNotParkedException;

    Iterator<Entry<Integer, DoublyLinkedList<UserInfo>>> listDelayed() throws NoDelaysException;

    Iterator<Entry<String, ParkInfo>> favouriteParks() throws NoPickUpsException;

}
