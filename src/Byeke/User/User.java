package Byeke.User;

import Byeke.PickUp.PickUp;

public interface User extends UserInfo {
    void addBalance(int balance);
    void pickUp(PickUp pickUp);
    void pickDown();
}
