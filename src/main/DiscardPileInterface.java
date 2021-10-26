import java.util.List;
import java.util.Optional;

public interface DiscardPileInterface {

    public Optional<CardInterface> getTopCard();
    public void addCards(List<CardInterface> cards);
    public int getSize();
    public List<CardInterface> shuffle();
}
