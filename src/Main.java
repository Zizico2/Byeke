import dataStructures.*;
import Byeke.*;
import java.util.Scanner;


public class Main {

    // Contem as mensagens a serem impressas,
    private enum Message {

        ;
        private final String msg;

        Message(String msg) {
            this.msg = msg;
        }

        public String getMsg(){
            return msg;
        }
    }

    // Contem os comandos.
    private enum Command {

        ADDUSER(),
        REMOVEUSER(),
        GETUSERINFO(),
        ADDPARK(),
        ADDBIKE(),
        REMOVEBIKE(),
        GETPARKINFO(),
        PICKUP(),
        PICKDOWN(),
        CHARGEUSER(),
        BIKEPICKUPS(),
        USERPICKUPS(),
        PARKEDBIKE(),
        LISTDELAYED(),
        FAVORITEPARKS(),
        XS();

        Command() {
        }
    }

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
            try {
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
                        System.out.println(Message.EXITING.msg);
                        in.close();

                }
            } catch (IllegalArgumentException e) {
                //System.out.println(Message.UNKNOWN.msg);
            }
        } while (cmd == null || !cmd.equals(Command.XS));
    }

    private static void processXS(Byeke byeke) {

    }

    private static void processFavoriteParks(Byeke byeke) {

    }

    private static void processListDelayed(Byeke byeke) {

    }

    private static void processParkedBike(Scanner in, Byeke byeke) {

    }

    private static void processUserPickUps(Scanner in, Byeke byeke) {


    }

    private static void processBikePickUps(Byeke byeke) {


    }

    private static void processChargeUser(Scanner in, Byeke byeke) {

    }

    private static void processPickDown(Scanner in, Byeke byeke) {

    }

    private static void processPickUp(Scanner in, Byeke byeke) {

    }

    private static void processGetParkInfo(Scanner in, Byeke byeke) {

    }

    private static void processRemoveBike(Scanner in, Byeke byeke) {

    }

    private static void processAddBike(Scanner in, Byeke byeke) {

    }

    private static void processAddPark(Scanner in, Byeke byeke) {

    }

    private static void processGetUserInfo(Scanner in, Byeke byeke) {
        String ID = in.nextLine();

        try {
            Iterator userInfo  = byeke.getUserInfo(ID);

            String userInfoString = "";

            //TODO

            System.out.println(userInfoString);
        } catch (InvalidUserIDException e){
            System.out.println(e.getMessage());
        }
    }

    private static void processRemoveUser(Scanner in, Byeke byeke) {
        String ID = in.nextLine();

        try {
            byeke.removeUser(ID);
            System.out.println(Message.userRemovedSuccessfully.getMsg());
        } catch (InvalidUserIDException e){
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
        String adress = in.nextLine();

        try {
            byeke.addUser(ID,tin,emailAdress,phoneNumber,name,adress);
            System.out.println(Message.userAddedSuccessfully.getMsg());
        } catch (DuplicateUserIDException e){
            System.out.println(e.getMessage());
        }


    }
}
