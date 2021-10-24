import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hand {

    private List<CardInterface> cards = new ArrayList<>();

    public void addCards(List<CardInterface> cards) {
        this.cards.addAll(cards);
    }

    public List<CardInterface> getCards() {
        return cards;
    }

    public boolean isActionCard(int cardIndex) {
        if (cardIndex >= cards.size()) throw new IndexOutOfBoundsException();
        return (cards.get(cardIndex).cardType().isAction());
    }

    public Optional<CardInterface> play(int cardIndex) {
        if (cardIndex >= cards.size() || !isActionCard(cardIndex)) return Optional.empty();
        CardInterface cardPlayed = cards.remove(cardIndex);
        return Optional.of(cardPlayed);
    }
}
