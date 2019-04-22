package card.operations;

import card.operations.CardOperation;

public class PayMoneyOperation extends CardOperation {
    private String serialNumber = null;
    private double money = 0.0;

    public PayMoneyOperation() {
        this("none", 0.0);
    }

    public PayMoneyOperation(String serialNumber, double money) {
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
