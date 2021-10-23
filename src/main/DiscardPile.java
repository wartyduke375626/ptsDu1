import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DiscardPile {
    List<CardInterface> cards;

    public DiscardPile(List<CardInterface> _cards) {
        cards = _cards;
    }
        
    public Optional<CardInterface> getTopCard() {
    	if (cards.isEmpty()) return Optional.empty();
        return Optional.of(cards.get(cards.size()-1));
    }
        
    public void addCards(List<CardInterface> _cards) {
        cards.addAll(_cards);
    }
        
    public int getSize() {
        return cards.size();
    }
        
    public List<CardInterface> shuffle() {
        Collections.shuffle(cards);
        List<CardInterface> cards_to_send = cards;        
        cards = new ArrayList<CardInterface>();
        return cards_to_send;
    }
}
        
        
