import java.util.ArrayList;
import java.util.List;

public class Deck{

    private List<CardInterface> cards = new ArrayList<>();
    private final DiscardPile discardPile;

    public Deck(List<CardInterface> initialCards, DiscardPile discardPile) {
        cards.addAll(initialCards);
        this.discardPile = discardPile;
    }

    public List<CardInterface> draw(int count) {
        if (count > discardPile.getSize() + cards.size()) throw new IndexOutOfBoundsException();
        List<CardInterface> drawnCards = new ArrayList<>();
        for (int i=0; i<count; i++) {
            if (cards.size() == 0) cards.addAll(discardPile.shuffle());
            int lastIndex = cards.size()-1;
            drawnCards.add(cards.remove(lastIndex));
        }
        return drawnCards;
    }
}
