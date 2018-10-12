package Byeke.Park;

public interface ParkInfo extends java.io.Serializable {

    String getName();

    String getAddress();

    boolean hasParkedBikes();

    String getId();

    int getNoPickUps();

    boolean hasPickUps();

    int getNoParkedBikes();
}
