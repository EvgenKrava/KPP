package card.operations;

import card.MetroCard;

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
}
