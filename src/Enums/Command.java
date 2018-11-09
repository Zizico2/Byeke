package Enums;

/**
 * @author Bernardo Antonio Borda d'Agua - 53648
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
