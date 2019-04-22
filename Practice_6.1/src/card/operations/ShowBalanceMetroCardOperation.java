package card.operations;

public class ShowBalanceMetroCardOperation extends MetroCardOperation {
    String serialNumber;

    public ShowBalanceMetroCardOperation(String serialNumber){
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
