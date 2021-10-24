import java.util.ArrayList;
import java.util.List;

public class Play {

    private List<CardInterface> cardsInPlay = new ArrayList<>();

    public void putTo(CardInterface card) {
        cardsInPlay.add(card);
    }

    public List<CardInterface> throwAll() {
        List<CardInterface> tmp = new ArrayList<>(cardsInPlay);
        cardsInPlay = new ArrayList<>();
        return tmp;
    }
}
