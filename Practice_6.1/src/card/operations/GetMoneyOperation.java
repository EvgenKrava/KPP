package card.operations;

public class GetMoneyOperation extends MetroCardOperation {
    String serialNumber;
    double money;

    public GetMoneyOperation(String serialNumber, double money){
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
