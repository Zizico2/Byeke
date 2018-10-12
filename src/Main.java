import Byeke.*;
import Byeke.Park.ParkInfo;
import Byeke.User.UserInfo;
import Enums.*;
import Exceptions.*;

import java.sql.SQLOutput;
import java.util.Formatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Byeke byeke = new ByekeClass();
        Scanner in = new Scanner(System.in);
        executeCommand(in, byeke);
    }

    private static Command getCommand(Scanner in) {

        String cmd = in.next();

        return Command.valueOf(cmd.toUpperCase());
    }

    private static void executeCommand(Scanner in, Byeke byeke) {
        Command cmd = null;
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
                        processBikePickUps(byeke);
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

                    case XS:
                        processXS(byeke);
                        System.out.println(Message.EXITING.getMessage());
                        in.close();
                }
            System.out.println();
        } while (!cmd.equals(Command.XS));
    }

    private static void processXS(Byeke byeke) {

    }

    private static void processFavoriteParks(Byeke byeke) {
        try {


        } catch (NoPickUpsException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processListDelayed(Byeke byeke) {
        try {


        } catch (NoDelaysException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processParkedBike(Scanner in, Byeke byeke) {
        try {


        } catch (InexistantBikeIdException | InexistantParkIdException | BikeNotParkedException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processUserPickUps(Scanner in, Byeke byeke) {
        try {


        } catch (InexistantUserIdException | UserHasNoPickUpsException | UserOnTheMoveException e){
            System.out.println(e.getMessage());

        }

    }

    private static void processBikePickUps(Byeke byeke) {
        try {


        } catch (InexistantBikeIdException | BikeHasNoPickUpsException | BikeOnTheMoveException e){
            System.out.println(e.getMessage());

        }

    }

    private static void processChargeUser(Scanner in, Byeke byeke) {
        try {


        } catch (InexistantUserIdException | InvalidValueException e){
            System.out.println(e.getMessage());

        }
    }

    private static void processPickDown(Scanner in, Byeke byeke) {
        try {


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
        String name = in.next();
        in.nextLine();
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
        String name = in.next();
        in.nextLine();
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
