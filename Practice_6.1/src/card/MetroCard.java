package card;

import java.io.Serializable;

public class MetroCard implements Serializable {
    private String serialNumber;
    private User user;
    private String college;
    private double balance;

    public MetroCard(String serialNumber, User user, String college) {
        this.serialNumber = serialNumber;
        this.user = user;
        this.college = college;
        this.balance = 0;
    }

    @Override
    public String toString() {
        return "№: " + serialNumber + "\nПользователь: " + user +
                "\nУниверситет: " + college +
                "\nБаланс: " + balance;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public User getUser() {
        return user;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
