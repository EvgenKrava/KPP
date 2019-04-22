package card.operations;

import card.operations.CardOperation;

public class RemoveCardOperation extends CardOperation {

    private String serialNumber = null;

    public RemoveCardOperation(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public RemoveCardOperation(){

    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
