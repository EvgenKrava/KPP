package other;

import java.util.ArrayList;

public class MetroCardBank {
    private ArrayList<MetroCard> store;

    public MetroCardBank(){
        store = new ArrayList<MetroCard>();
    }

    public ArrayList<MetroCard> getStore() {
        return store;
    }

    public void setStore(ArrayList<MetroCard> store) {
        this.store = store;
    }

    public int findMetroCard(String serialNumber){
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getSerialNumber().equals(serialNumber))
                return i;
        }
        return -1;
    }

    public int numCard(){
        return store.size();
    }

    public void addCard(MetroCard metroCard){
        store.add(metroCard);
    }

    public boolean removeCard(String serialNumber){
        return store.remove(store.get(findMetroCard(serialNumber)));
    }

    public boolean addMoney(String serialNumber, double money){
        try {
            MetroCard metroCard = store.get(findMetroCard(serialNumber));
            metroCard.setBalance(metroCard.getBalance()+money);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("List of MetroCards:");
        for (MetroCard c : store) {
            buf.append("\n\n").append(c);
        }
        return buf.toString();
    }

    public boolean getMoney(String serialNumber, double money) {
        MetroCard metroCard = store.get(findMetroCard(serialNumber));
        metroCard.setBalance(metroCard.getBalance()-money);
        return true;
    }

    public double getBalance(String serialNumber){
        return store.get(findMetroCard(serialNumber)).getBalance();
    }
}
