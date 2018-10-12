import Byeke.*;
import Byeke.Park.ParkInfo;
import Byeke.PickUp.PickUpInfo;
import Byeke.User.UserInfo;
import Enums.*;
import Exceptions.*;
import dataStructures.Iterator;

import java.io.*;
import java.util.Scanner;




public class Main {
    private static final String FILE = "save_file.data";

    public static void main(String[] args) {
        Byeke byeke = loadProgram();
        Scanner in = new Scanner(System.in);
        executeCommand(in, byeke);
    }

    private static Command getCommand(Scanner in) {

        String cmd = in.next();

        return Command.valueOf(cmd.toUpperCase());
    }

    private static void executeCommand(Scanner in, Byeke byeke) {
        Command cmd;
        do {
                cmd = getCommand(in);
                switch (cmd) {
                    case ADDUSER:
                        processAddUser(in, byeke);
                        break;

                    case REMOVEUSER:
                        processRemoveUser(in, byeke);
                        break;

                    case GETUSERINFO:
                        processGetUserInfo(in,byeke);
                        break;

                    case ADDPARK:
                        processAddPark(in, byeke);
                        break;

                    case ADDBIKE:
                        processAddBike(in, byeke);
                        break;

                    case REMOVEBIKE:
                        processRemoveBike(in, byeke);
                        break;

                    case GETPARKINFO:
                        processGetParkInfo(in, byeke);
                        break;

                    case PICKUP:
                        processPickUp(in, byeke);
                        break;

                    case PICKDOWN:
                        processPickDown(in, byeke);
                        break;

                    case CHARGEUSER:
                        processChargeUser(in, byeke);
                        break;

                    case BIKEPICKUPS:
                        processBikePickUps(in, byeke);
                        break;

                    case USERPICKUPS:
                        processUserPickUps(in, byeke);
                        break;

                    case PARKEDBIKE:
                        processParkedBike(in, byeke);
                        break;

                    case LISTDELAYED:
                        processListDelayed(byeke);
                        break;

                    case FAVORITEPARKS:
                        processFavoriteParks(byeke);

                }
            if (!cmd.equals(Command.XS))
                    System.out.println();

        } while (!cmd.equals(Command.XS));
        exitProgram(in,byeke);
    }

    private static Byeke loadProgram(){
        try{
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(FILE));
            Byeke byeke = (Byeke)file.readObject();
            file.close();
            return byeke;
        }catch (IOException|ClassNotFoundException e){

        }
        return new ByekeClass();
    }

    private static void exitProgram(Scanner in, Byeke byeke) {
        in.nextLine();
        in.nextLine();
        System.out.println(Message.EXITING.getMessage());
        in.close();

        try{
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(FILE));
            file.writeObject(byeke);
            file.flush();
            file.close();
        } catch (IOException e){

        }
    }

    private static void processFavoriteParks(Byeke byeke) {
        try {
            ParkInfo parkInfo = byeke.favouriteParks();
            System.out.printf(Message.FAVOURITE_PARKS_SUCCESS.getMessage(), parkInfo.getName(), parkInfo.getAddress(), parkInfo.getNoPickUps());
        } catch (NoPickUpsException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processListDelayed(Byeke byeke) {
        try {
            UserInfo userinfo = byeke.listDelayed();
            System.out.printf(Message.LIST_DELAYED_SUCCESS.getMessage(), userinfo.getName(), userinfo.getTin(), userinfo.getAddress(), userinfo.getEmailAddress(), userinfo.getPhoneNumber(), userinfo.getBalance(), userinfo.getPoints());
        } catch (NoDelaysException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processParkedBike(Scanner in, Byeke byeke) {
        String bikeId = in.next();
        String parkId = in.next();
        in.nextLine();
        in.nextLine();

        try {
            byeke.parkedBike(bikeId,parkId);
            System.out.println(Message.PARKED_BIKE_SUCCESS.getMessage());

        } catch (InexistantBikeIdException | InexistantParkIdException | BikeNotParkedException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processUserPickUps(Scanner in, Byeke byeke) {
        String userId = in.next();
        in.nextLine();
        in.nextLine();

        try {
            Iterator<PickUpInfo> userPickUps = byeke.userPickUps(userId);
            PickUpInfo pickUpInfo;
            userPickUps.rewind();
            while(userPickUps.hasNext()) {
                pickUpInfo = userPickUps.next();
                System.out.printf(Message.USER_PICKUPS_SUCCESS.getMessage(), pickUpInfo.getBikeInfo().getId(), pickUpInfo.getInitialParkInfo().getId(), pickUpInfo.getFinalParkInfo().getId(), pickUpInfo.getTime(), pickUpInfo.getDelay(), pickUpInfo.getCost());
            }

        } catch (InexistantUserIdException | UserHasNoPickUpsException | UserOnTheMoveException e){
            System.out.println(e.getMessage());

        }

    }

    private static void processBikePickUps(Scanner in, Byeke byeke) {
        String bikeId = in.next();
        in.nextLine();
        in.nextLine();

        try {
            Iterator<PickUpInfo> bikePickUps = byeke.bikePickUps(bikeId);
            PickUpInfo pickUpInfo;
            bikePickUps.rewind();
            while(bikePickUps.hasNext()) {
                pickUpInfo = bikePickUps.next();
                System.out.printf(Message.BIKE_PICKUPS_SUCCESS.getMessage(), pickUpInfo.getUserInfo().getId(), pickUpInfo.getInitialParkInfo().getId(), pickUpInfo.getFinalParkInfo().getId(), pickUpInfo.getTime(), pickUpInfo.getDelay(), pickUpInfo.getCost());
            }

        } catch (InexistantBikeIdException | BikeHasNoPickUpsException | BikeOnFirstPickUpException e){
            System.out.println(e.getMessage());

        }

    }

    private static void processChargeUser(Scanner in, Byeke byeke) {
        String userId = in.next();
        int value = in.nextInt();
        in.nextLine();
        in.nextLine();

        try {
            UserInfo userInfo = byeke.ChargeUser(userId,value);
            System.out.printf(Message.CHARGE_USER_SUCCESS.getMessage(), userInfo.getBalance());

        } catch (InexistantUserIdException | InvalidValueException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processPickDown(Scanner in, Byeke byeke) {
        String bikeId = in.next();
        String parkId = in.next();
        int time = in.nextInt();
        in.nextLine();
        in.nextLine();


        try {
            UserInfo userInfo = byeke.pickDown(bikeId, parkId, time);
            System.out.printf(Message.BIKE_PICKED_DOWN_SUCCESSFULLY.getMessage(), userInfo.getBalance(), userInfo.getPoints());

        } catch (InexistantBikeIdException | InexistantParkIdException | BikeNotOnTheMoveException | InvalidTimeException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processPickUp(Scanner in, Byeke byeke) {
        String bikeId = in.next();
        String userId = in.next();
        in.nextLine();
        in.nextLine();

        try {
            byeke.pickUp(bikeId,userId);
            System.out.println(Message.BIKE_PICKED_UP_SUCCESSFULLY.getMessage());

        } catch (InexistantBikeIdException | InexistantUserIdException | BikeOnTheMoveException | UserOnTheMoveException | LowBalanceException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processGetParkInfo(Scanner in, Byeke byeke) {
        String iD = in.next();
        in.nextLine();
        in.nextLine();

        try {
            ParkInfo parkInfo = byeke.getParkInfo(iD);
            System.out.printf(Message.PARK_INFO_SUCCESS.getMessage(), parkInfo.getName(), parkInfo.getAddress(), parkInfo.getNoParkedBikes());

        } catch (InexistantParkIdException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processRemoveBike(Scanner in, Byeke byeke) {
        String iD = in.next();
        in.nextLine();
        in.nextLine();

        try {
            byeke.removeBike(iD);
            System.out.println(Message.BIKE_REMOVED_SUCCESSFULLY.getMessage());
        } catch (InexistantBikeIdException | BikeHasPickUpsException e){
            System.out.println(e.getMessage());
        }
    }

    private static void processAddBike(Scanner in, Byeke byeke) {
        String iD = in.next();
        String parkId = in.next();
        String plate = in.next();
        in.nextLine();
        in.nextLine();

        try {
            byeke.addBike(iD, parkId, plate);
            System.out.println(Message.BIKE_ADDED_SUCCESSFULLY.getMessage());

        } catch (DuplicateBikeIdException | InexistantParkIdException e){
            System.out.println(e.getMessage());
        }
    }

    private static void processAddPark(Scanner in, Byeke byeke) {
        String iD = in.next();
        String name = in.nextLine().trim();
        String address = in.nextLine();
        in.nextLine();

        try {
            byeke.addPark(iD, name, address);
            System.out.println(Message.PARK_ADDED_SUCCESSFULLY.getMessage());

        } catch (DuplicateParkIdException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processGetUserInfo(Scanner in, Byeke byeke) {
        String iD = in.next();
        in.nextLine();
        in.nextLine();
        try {
            UserInfo userInfo = byeke.getUserInfo(iD);
            System.out.printf(Message.USER_INFO_SUCCESS.getMessage(), userInfo.getName(), userInfo.getTin(), userInfo.getAddress(), userInfo.getEmailAddress(), userInfo.getPhoneNumber(), userInfo.getBalance(), userInfo.getPoints());
        } catch (InexistantUserIdException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processRemoveUser(Scanner in, Byeke byeke) {
        String iD = in.next();
        in.nextLine();
        in.nextLine();
        try {
            byeke.removeUser(iD);
            System.out.println(Message.USER_REMOVED_SUCCESSEFULLY.getMessage());
        } catch (InexistantUserIdException | UserHasPickUpsException e) {
            System.out.println(e.getMessage());

        }
    }

    private static void processAddUser(Scanner in, Byeke byeke) {
        String ID = in.next();
        String tin = in.next();
        String emailAdress = in.next();
        String phoneNumber = in.next();
        String name = in.nextLine().trim();
        String address = in.nextLine();
        in.nextLine();

        try {
            byeke.addUser(ID,tin,emailAdress,phoneNumber,name,address);
            System.out.println(Message.USER_ADDED_SUCCESSFULLY.getMessage());
        } catch (DuplicateUserIdException e){
            System.out.println(e.getMessage());
        }


    }
}
