package Byeke.User;

import Byeke.PickUp.PickUpInfo;
import dataStructures.Iterator;

public interface UserInfo extends java.io.Serializable {

    String getId();

    String getName();

    String getAddress();

    String getEmailAddress();

    String getPhoneNumber();

    String getTin();

    int getBalance();

    int getPoints();

    boolean hasDelays();

    boolean hasPickUps();

    boolean isOnFirstPickup();

    Iterator<PickUpInfo> getArchivedPickUps();

    boolean isMoving();
}
