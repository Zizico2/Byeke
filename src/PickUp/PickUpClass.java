package PickUp;

public class PickUpClass {
    private static final int MAX_TIME = 60;
    private static final int PAYMENT_PERIOD = 30;

    private String initialParkId;
    private String finalParkId;
    private int time;
    private int delay;
    private int cost;

    public PickUpClass(String initialParkId) {
        this.initialParkId = initialParkId;
    }

    public double endPickUp(String finalParkId, int time){
        this.finalParkId = finalParkId;
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

    public String getInitialParkId() {
        return initialParkId;
    }

    public String getFinalParkId() {
        return finalParkId;
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
