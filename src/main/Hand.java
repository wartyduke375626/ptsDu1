import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hand {

    private List<CardInterface> cardsInHand = new ArrayList<>();

    public void addCards(List<CardInterface> cards) {
        this.cardsInHand.addAll(cards);
    }

    public int getSize() {
        return cardsInHand.size();
    }

    public List<CardInterface> throwAll() {
        List<CardInterface> tmp = new ArrayList<>(cardsInHand);
        cardsInHand = new ArrayList<>();
        return tmp;
    }

    public boolean isActionCard(int cardIndex) {
        if (cardIndex >= cardsInHand.size()) throw new IndexOutOfBoundsException();
        return (cardsInHand.get(cardIndex).getCardType().isAction());
    }

    public Optional<CardInterface> play(int cardIndex) {
        if (!isActionCard(cardIndex)) return Optional.empty();
        CardInterface cardPlayed = cardsInHand.remove(cardIndex);
        return Optional.of(cardPlayed);
    }
}
