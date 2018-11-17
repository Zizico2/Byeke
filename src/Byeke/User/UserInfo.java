package Byeke.User;

import Byeke.PickUp.PickUpInfo;
import Byeke.Tags.Nameable;
import dataStructures.Iterator;

public interface UserInfo extends java.io.Serializable, Nameable {

    String getId();

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
