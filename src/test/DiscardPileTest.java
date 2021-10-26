import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DiscardPileTest  {

    private DiscardPile pile1;
    private DiscardPile pile2;

    private void assertTopIs(DiscardPile pile, String string) {
        assertTrue(pile.getTopCard().isPresent());
        assertEquals(pile.getTopCard().get().getGameCardType().getName(), string);
    }
        
    private void assertTopIsNone(DiscardPile  pile) {
        assertTrue(pile.getTopCard().isEmpty());
    }

    private void setUp() {
        pile1 = new DiscardPile(new ArrayList<CardInterface>() {{
            add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
            add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        }});
        pile2 = new DiscardPile(new ArrayList<CardInterface>());
    }
    
    @Test
    public void test_get_top_card() {
    	setUp();
        assertTopIs(pile1, "Copper");
        assertTopIsNone(pile2);
    }
        
    @Test
    public void test_add_cards_and_get_size() {
    	setUp();
        assertEquals(pile2.getSize(), 0);
        pile2.addCards(new ArrayList<CardInterface>() {{
            add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        }});
        assertEquals(pile2.getSize(), 1);
        assertTopIs(pile2, "Estate");
        pile2.addCards(new ArrayList<CardInterface>() {{
            add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        }});
        assertEquals(pile2.getSize(), 2);
        assertTopIs(pile2, "Copper");
    }
 }
        
