package Byeke.Park;

import Byeke.Bike.Bike;
import dataStructures.List;

public class ParkClass implements Park {

    private String iD;

    private String address;

    private String name;

    private Bike parkedBike;

    private int pickUps;


    public static ParkClass createPark(String iD, String name, String address){
        return new ParkClass(iD,name,address);
    }

    private ParkClass (String iD, String name, String address){
        this.iD = iD;
        this.name = name;
        this.address = address;
        this.parkedBike = null;
        this.pickUps = 0;
    }


    @Override
    public void addBike(Bike bike) {
        parkedBike =  bike;
    }

    @Override
    public void removeBike(Bike bike) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNoPickUps() {
        return pickUps;
    }

    @Override
    public boolean hasPickUps() {
        return pickUps!=0;
    }

    @Override
    public int getNoParkedBikes() {
        return hasParkedBikes() ? 1 : 0;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public boolean hasParkedBikes() {
        return parkedBike != null;
    }

    @Override
    public String getId() {
        return iD;
    }


}
