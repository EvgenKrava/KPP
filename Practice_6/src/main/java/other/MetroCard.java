package other;

import java.io.Serializable;

public class MetroCard implements Serializable {
    private String serialNumber;
    private User user;
    private String college;
    private double balance;

    public MetroCard(String serialNumber,User user, String college, double balance){
        this.user=user;
        this.serialNumber=serialNumber;
        this.college =college;
        this.balance=balance;
    }

    public MetroCard(){
        user = new User();
        serialNumber = "none";
        college = "none";
        balance = 0;
    }

    @Override
    public String toString() {
        return "№: " + serialNumber + "\nПользователь: " + user +
                "\nУниверситет: " + college +
                "\nБаланс: " + balance;
    }

    public User getUser() {
        return user;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getCollege() {
        return college;
    }

    public double getBalance() {
        return balance;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}