package card.operations;

public class ShowBalanceOperation extends CardOperation {

    private String serialNumber ;

    private double balance = 0.0;

    public ShowBalanceOperation(String serialNumber){
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }


    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
