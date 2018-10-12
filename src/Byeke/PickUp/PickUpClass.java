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

    private int time;
   // private int delay;
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
        //if (time > MAX_TIME) {
        //    delay = time - MAX_TIME;
          //  cost = (int)Math.ceil(delay/(double)PAYMENT_PERIOD);
        //} else{
          //  delay = 0;
            //cost = 0;
        //}
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
        return 0;
    }

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
