package gameComponents;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DiscardPile implements DiscardPileInterface {
    List<CardInterface> cards = new ArrayList<>();

    public DiscardPile(List<CardInterface> cards) {
        this.cards.addAll(cards);
    }

    @Override
    public Optional<CardInterface> getTopCard() {
    	if (cards.isEmpty()) return Optional.empty();
        return Optional.of(cards.get(cards.size()-1));
    }

    @Override
    public void addCards(List<CardInterface> cards) {
        this.cards.addAll(cards);
    }

    @Override
    public void addCards(CardInterface... cards) {
        this.cards.addAll(List.of(cards));
    }

    @Override
    public int getSize() {
        return cards.size();
    }

    @Override
    public List<CardInterface> shuffle() {
        Collections.shuffle(cards);
        List<CardInterface> cardsToSend = cards;
        cards = new ArrayList<>();
        return cardsToSend;
    }
}
        
        
