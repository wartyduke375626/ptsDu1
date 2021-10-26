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
        if (cardIndex >= cardsInHand.size()) throw new IndexOutOfBoundsException();
        return (cardsInHand.get(cardIndex).getGameCardType().isAction());
    }

    @Override
    public Optional<CardInterface> play(int cardIndex) {
        if (!isActionCard(cardIndex)) return Optional.empty();
        CardInterface cardPlayed = cardsInHand.remove(cardIndex);
        return Optional.of(cardPlayed);
    }
}
