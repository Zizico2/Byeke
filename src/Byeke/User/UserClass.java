package Byeke.User;

public class UserClass
{

    private String name;
    private String adress;
    private String emailAdress;
    private String phoneNumber;
    private String tin;
    private int balance;
    private int points;

    public UserClass(String name, String adress, String emailAdress, String phoneNumber, String tin) {
        this.name = name;
        this.adress = adress;
        this.emailAdress = emailAdress;
        this.phoneNumber = phoneNumber;
        this.tin = tin;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTin() {
        return tin;
    }

    public int getBalance() {
        return balance;
    }

    public int getPoints() {
        return points;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
