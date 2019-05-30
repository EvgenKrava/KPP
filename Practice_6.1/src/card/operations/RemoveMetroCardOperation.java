package card.operations;

import card.MetroCardBank;

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

    @Override
    public String operate(MetroCardBank metroCardBank) {
        if (metroCardBank.removeCard(serialNumber))
        return "Card deleted";
        else return "Card not deleted";
    }
}
