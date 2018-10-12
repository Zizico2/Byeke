package Byeke.PickUp;

import Byeke.Bike.Bike;
import Byeke.Bike.BikeInfo;
import Byeke.Park.Park;
import Byeke.Park.ParkInfo;
import Byeke.User.User;
import Byeke.User.UserInfo;

public class PickUpClass implements PickUp{
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final int MAX_TIME = 60;
    private static final int PAYMENT_PERIOD = 30;

    private Park initialPark;
    private Park finalPark;
    private Bike bike;
    private User user;

    private int cost;
    private int time;
    private int delay;

    public static PickUpClass createPickUp(Park initialPark,Bike bike, User user){
        return new PickUpClass(initialPark, bike, user);
    }



    private PickUpClass(Park initialPark, Bike bike, User user) {
        this.initialPark = initialPark;
        this.bike = bike;
        this.user = user;
        this.cost = 0;
        this.time = 0;
        this.delay = 0;
    }

    @Override
    public void pickDown(Park finalPark, int time){
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

    @Override
    public Bike getBike() {
        return bike;
    }


}
