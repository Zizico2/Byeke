package Byeke.PickUp;

import Byeke.Bike.Bike;
import Byeke.Park.Park;
import Byeke.Park.ParkInfo;
import Byeke.User.User;

public class PickUpClass implements PickUp{
    private static final int MAX_TIME = 60;
    private static final int PAYMENT_PERIOD = 30;

    private Park initialPark;
    private Park finalPark;
    private Bike bike;
    private User user;

    private int time;
    private int delay;
    private int cost;

    public static PickUpClass createPickUp(Park initialPark,Bike bike, User user){
        return new PickUpClass(initialPark, bike, user);
    }



    private PickUpClass(Park initialPark, Bike bike, User user) {
        this.initialPark = initialPark;
        this.bike = bike;
        this.user = user;
    }

    public int pickDown(Park finalPark, int time){
        this.finalPark = finalPark;
        if (time > MAX_TIME) {
            delay = time - MAX_TIME;
            cost = (int)Math.ceil(delay/(double)PAYMENT_PERIOD);
        } else{
            delay = 0;
            cost = 0;
        }
        this.time = time;
        return cost;
    }

    public ParkInfo getInitialParkInfo() {
        return initialPark;
    }

    public ParkInfo getFinalParkInfo() {
        return finalPark;
    }

    public int getTime() {
        return time;
    }

    public int getDelay() {
        return delay;
    }

    public int getCost() {
        return cost;
    }


}
