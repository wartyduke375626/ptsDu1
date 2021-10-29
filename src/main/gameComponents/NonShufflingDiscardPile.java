package gameComponents;

import java.util.ArrayList;
import java.util.List;

public class NonShufflingDiscardPile extends DiscardPile {

    public NonShufflingDiscardPile(List<CardInterface> cards) {
        super(cards);
    }

    @Override
    public List<CardInterface> shuffle() {
        List<CardInterface> cardsToSend = cards;
        cards = new ArrayList<>();
        return cardsToSend;
    }
}
