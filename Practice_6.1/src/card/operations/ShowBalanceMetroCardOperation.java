package card.operations;

import card.MetroCardBank;

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

    @Override
    public String operate(MetroCardBank metroCardBank) {
        System.out.println(metroCardBank);
        return metroCardBank.getMetroCardForSerialNumber(serialNumber).toString();
    }
}
