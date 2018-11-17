package Byeke.User;

import Byeke.PickUp.PickUpInfo;
import Byeke.Tags.Nameable;
import dataStructures.Iterator;

public interface UserInfo extends java.io.Serializable, Nameable {
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

    boolean hasDelays();

    boolean hasPickUps();

    boolean isOnFirstPickup();

    Iterator<PickUpInfo> getArchivedPickUps();

    boolean isMoving();
}
