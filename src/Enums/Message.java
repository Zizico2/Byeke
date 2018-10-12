package Enums;


/**
 * Messages output by successefull commands.
 */
public enum Message {

    USER_ADDED_SUCCESSFULLY("Insercao de utilizador com sucesso."),
    USER_REMOVED_SUCCESSEFULLY("Utilizador removido com sucesso."),
    USER_INFO_SUCCESS("%s: %s, %s, %s, %s, %d, %d%n"),
    PARK_INFO_SUCCESS("%s: %s, %d%n"),
    PARK_ADDED_SUCCESSFULLY("Parque adicionado com sucesso."),
    BIKE_ADDED_SUCCESSFULLY("Bicicleta adicionada com sucesso."),
    BIKE_REMOVED_SUCCESSFULLY("Bicicleta removida com sucesso."),
    BIKE_PICKED_UP_SUCCESSFULLY("PickUp com sucesso."),
    BIKE_PICKED_DOWN_SUCCESSFULLY("Pickdown com sucesso: %d euros, %d pontos%n"),
    CHARGE_USER_SUCCESS("Saldo: %d euros%n"),
    PARKED_BIKE_SUCCESS("Bicicleta estacionada no parque."),
    USER_PICKUPS_SUCCESS("%s %s %s %d %d %d%n"),
    BIKE_PICKUPS_SUCCESS("%s %s %s %d %d %d%n"),
    LIST_DELAYED_SUCCESS("%s: %s, %s, %s, %s, %d, %d%n"),
    FAVOURITE_PARKS_SUCCESS("%s: %s, %d%n"),
    EXITING("Gravando e terminando...");





    private final String message;
    Message(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

}
