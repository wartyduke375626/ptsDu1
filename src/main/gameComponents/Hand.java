package gameComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hand implements HandInterface {

    private List<CardInterface> cardsInHand = new ArrayList<>();

    @Override
    public void addCards(List<CardInterface> cards) {
        this.cardsInHand.addAll(cards);
    }

    @Override
    public int getSize() {
        return cardsInHand.size();
    }

    @Override
    public List<CardInterface> throwAll() {
        List<CardInterface> tmp = new ArrayList<>(cardsInHand);
        cardsInHand = new ArrayList<>();
        return tmp;
    }

    @Override
    public boolean isActionCard(int cardIndex) {
        if (cardIndex < 0) throw new IllegalArgumentException("Negative cardIndex");
        if (cardIndex >= cardsInHand.size()) return false;
        return (cardsInHand.get(cardIndex).getGameCardType().isAction());
    }

    @Override
    public Optional<CardInterface> play(int cardIndex) {
        if (cardIndex < 0) throw new IllegalArgumentException("Negative cardIndex");
        if (cardIndex >= cardsInHand.size()) return Optional.empty();
        CardInterface cardPlayed = cardsInHand.remove(cardIndex);
        return Optional.of(cardPlayed);
    }

    @Override
    public Optional<CardInterface> lookAt(int cardIndex) {
        if (cardIndex < 0) throw new IllegalArgumentException("Negative cardIndex");
        if (cardIndex >= cardsInHand.size()) return Optional.empty();
        return Optional.of(cardsInHand.get(cardIndex));
    }
}
