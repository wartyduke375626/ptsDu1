package gameComponentsTests;

import gameComponents.CardInterface;
import gameComponents.GameCardType;
import gameComponents.Play;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class PlayTest {

    private Play play;
    private Set<CardInterface> cardsPutToPlay = new HashSet<>();

    private void setUp() {
        play = new Play();
        CardInterface fakeCard1 = new FakeCard(GameCardType.GAME_CARD_TYPE_FESTIVAL);
        play.putTo(fakeCard1);
        cardsPutToPlay.add(fakeCard1);
        CardInterface fakeCard2 = new FakeCard(GameCardType.GAME_CARD_TYPE_SMITHY);
        play.putTo(fakeCard2);
        cardsPutToPlay.add(fakeCard2);
    }

    @Test
    public void test_throwAll() {
        setUp();
        Set<CardInterface> thrownCards = new HashSet<>(play.throwAll());
        assertEquals(cardsPutToPlay, thrownCards);
    }
}
