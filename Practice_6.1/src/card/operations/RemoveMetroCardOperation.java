package card.operations;

public class RemoveMetroCardOperation extends MetroCardOperation {
    String serialNumber;

    RemoveMetroCardOperation(String serialNumber){
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
