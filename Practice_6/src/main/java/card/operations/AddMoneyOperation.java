package card.operations;

import card.operations.CardOperation;

public class AddMoneyOperation extends CardOperation {
    private String serialNumber = null;
    private double money = 0.0;

    public AddMoneyOperation() {
        this("none", 0.0);
    }

    public AddMoneyOperation(String serialNumber, double money) {
        this.serialNumber = serialNumber;
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
