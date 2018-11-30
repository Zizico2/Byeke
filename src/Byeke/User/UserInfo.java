package Byeke.User;

import Byeke.PickUp.PickUpInfo;
import dataStructures.Iterator;

public interface UserInfo extends java.io.Serializable {
    /**
     * @return iD: Unique Identifier
     */
    String getId();

    /**
     *
     * @return addess: Address
     */
    String getAddress();

    /**
     *
     * @return emailAddress: Email Address
     */
    String getEmailAddress();

    /**
     *
     * @return name: Name
     */
    String getName();

    /**
     *
     * @return phoneNumber: Phone Number
     */
    String getPhoneNumber();

    /**
     *
     * @return tin: Taxpayer Identification Number
     */
    String getTin();

    /**
     *
     * @return balance: Current balance
     */
    int getBalance();

    /**
     *
     * @return points: Current points
     */
    int getPoints();

    /**
     *
     * @return true: if user has returned a bike late (Points > 0)
     */
    boolean hasDelays();

    boolean hasPickUps();

    boolean isOnFirstPickup();

    Iterator<PickUpInfo> getArchivedPickUps();

    boolean isMoving();
}
