import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Deck{

    // Cards in deck are represented by queue. The top of the deck is the front of the queue.
    private Queue<CardInterface> cards = new ArrayDeque<>();
    private final DiscardPileInterface discardPile;


    public Deck(DiscardPileInterface discardPile) {
        this.discardPile = discardPile;
    }

    public List<CardInterface> draw(int count) {
        if (count > cards.size()) {
            cards.addAll(discardPile.shuffle());
            if (count > cards.size()) count = cards.size();
        }
        List<CardInterface> drawnCards = new ArrayList<>();
        for (int i=0; i<count; i++) {
            drawnCards.add(cards.remove());
        }
        return drawnCards;
    }
}
