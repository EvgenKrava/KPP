package card.operations;

import other.MetroCard;

public class AddMetroCardOperation extends CardOperation {
    private MetroCard card = null;

    public AddMetroCardOperation(){
        card = new MetroCard();
    }

    public MetroCard getCard() {
        return card;
    }

    public void setCard(MetroCard card) {
        this.card = card;
    }
}
