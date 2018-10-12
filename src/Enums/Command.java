package Enums;

/**
 * Commands (user input)
 */
public enum Command {
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
