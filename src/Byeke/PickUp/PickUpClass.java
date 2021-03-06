package Byeke.PickUp;

import Byeke.Bike.Bike;
import Byeke.Bike.BikeInfo;
import Byeke.Park.Park;
import Byeke.Park.ParkInfo;
import Byeke.User.User;
import Byeke.User.UserInfo;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
 */
public class PickUpClass implements PickUp {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final int MAX_TIME = 60;
    private static final int PAYMENT_PERIOD = 30;

    private ParkInfo initialPark;
    private ParkInfo finalPark;
    private BikeInfo bike;
    private UserInfo user;

    private int cost;
    private int time;
    private int delay;

    public static PickUpClass createPickUp(ParkInfo initialPark, Bike bike, User user) {
        return new PickUpClass(initialPark, bike, user);
    }


    private PickUpClass(ParkInfo initialPark, Bike bike, User user) {
        this.initialPark = initialPark;
        this.bike = bike;
        this.user = user;
        this.cost = 0;
        this.time = 0;
        this.delay = 0;
    }

    @Override
    public void pickDown(ParkInfo finalPark, int time) {
        this.finalPark = finalPark;
        this.time = time;
    }

    @Override
    public ParkInfo getInitialParkInfo() {
        return initialPark;
    }

    @Override
    public ParkInfo getFinalParkInfo() {
        return finalPark;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public int getDelay() {
        return delay;
    }

    @Override
    public boolean isDelayed() {
        return getDelay() > 0;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public UserInfo getUserInfo() {
        return user;
    }

    @Override
    public BikeInfo getBikeInfo() {
        return bike;
    }
}
