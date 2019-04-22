package card.operations;

public class AddMoneyOperation extends MetroCardOperation {
    String serialNumber;
    double money;

    public AddMoneyOperation(String serialNumber, double money){
        this.money = money;
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getMoney() {
        return money;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
