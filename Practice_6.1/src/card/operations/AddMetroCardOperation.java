package card.operations;

import card.MetroCard;
import card.MetroCardBank;

public class AddMetroCardOperation extends MetroCardOperation {
    MetroCard metroCard;

    public AddMetroCardOperation(MetroCard metroCard){
        this.metroCard = metroCard;
    }

    public MetroCard getMetroCard() {
        return metroCard;
    }

    public void setMetroCard(MetroCard metroCard) {
        this.metroCard = metroCard;
    }

    @Override
    public String operate(MetroCardBank metroCardBank) {
        metroCardBank.addCard(metroCard);
        return "Card added";
    }
}
