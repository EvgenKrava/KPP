package card;

import java.util.ArrayList;

public class MetroCardBank {
    private ArrayList<MetroCard> metroCards;

    public MetroCardBank() {
        metroCards = new ArrayList<>();
    }

    public boolean addCard(MetroCard metroCard) {
        return metroCards.add(metroCard);
    }

    public MetroCard getMetroCardForSerialNumber(String serialNumber) {
        for (MetroCard m : metroCards) {
            if (m.getSerialNumber().equals(serialNumber))
                return m;
        }
        return null;
    }

    public boolean removeCard(String serialNumber) {
        return metroCards.remove(getMetroCardForSerialNumber(serialNumber));
    }

    public boolean addMoney(String serialNumber, double money) {
        if (money <= 0&&getMetroCardForSerialNumber(serialNumber)==null) return false;
        getMetroCardForSerialNumber(serialNumber).setBalance(getMetroCardForSerialNumber(serialNumber).getBalance() + money);
        return true;

    }

    public boolean getMoney(String serialNumber, double money){
        if (money <= 0&&getMetroCardForSerialNumber(serialNumber)==null) return false;
        getMetroCardForSerialNumber(serialNumber).setBalance(getMetroCardForSerialNumber(serialNumber).getBalance() - money);
        return true;
    }

    public int getCardNumbers(){
        return metroCards.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("List of MetroCards: ");
        for (MetroCard m : metroCards) {
            stringBuilder.append("\n").append(m);
        }
        return stringBuilder.toString();
    }
}
