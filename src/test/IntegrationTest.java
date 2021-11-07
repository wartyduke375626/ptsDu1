
import gameComponents.*;
import gameManagement.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class IntegrationTest {
    private final List<CardInterface> initialCards = new ArrayList<>();
    private final TurnStatus newTurnStatus = new TurnStatus(1,1,0);
    private DiscardPileInterface discardPile;
    private DeckInterface deck;
    private HandInterface hand;
    private PlayInterface play;
    private final Map<GameCardType, BuyDeckInterface> buyDecks = new HashMap<>();
    private TurnInterface turn;

    private EndGameStrategy endGameStrategy;

    private GameInterface game;

    private void setInitialCards() {
        for (int i=0; i<7; i++) initialCards.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        for (int i=0; i<3; i++) initialCards.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
    }

    private void setInitialBuyDecks() {
        int buyDeckSizes =  10;
        buyDecks.put(GameCardType.GAME_CARD_TYPE_MARKET, new BuyDeck(GameCardType.GAME_CARD_TYPE_MARKET, buyDeckSizes));
        buyDecks.put(GameCardType.GAME_CARD_TYPE_ESTATE, new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, buyDeckSizes));
        buyDecks.put(GameCardType.GAME_CARD_TYPE_COPPER, new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, buyDeckSizes));
        buyDecks.put(GameCardType.GAME_CARD_TYPE_SMITHY, new BuyDeck(GameCardType.GAME_CARD_TYPE_SMITHY, buyDeckSizes));
        buyDecks.put(GameCardType.GAME_CARD_TYPE_VILLAGE, new BuyDeck(GameCardType.GAME_CARD_TYPE_VILLAGE, buyDeckSizes));
        buyDecks.put(GameCardType.GAME_CARD_TYPE_FESTIVAL, new BuyDeck(GameCardType.GAME_CARD_TYPE_FESTIVAL, buyDeckSizes));
        buyDecks.put(GameCardType.GAME_CARD_TYPE_LABORATORY, new BuyDeck(GameCardType.GAME_CARD_TYPE_LABORATORY, buyDeckSizes));
        buyDecks.put(GameCardType.GAME_CARD_TYPE_PROVINCE, new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, buyDeckSizes));
    }

    private void setEndGameStrategy() {
        List<EndGameStrategy> endGameStrategies = new ArrayList<>();
        EndGameStrategy atLeast3EmptyDecks = new AtLeastNEmptyDecks(3, new ArrayList<>(buyDecks.values()));
        endGameStrategies.add(atLeast3EmptyDecks);
        List<BuyDeckInterface> provinceDeck = new ArrayList<>();
        provinceDeck.add(buyDecks.get(GameCardType.GAME_CARD_TYPE_PROVINCE));
        EndGameStrategy provinceDeckEmpty = new AtLeastNEmptyDecks(1, provinceDeck);
        endGameStrategies.add(provinceDeckEmpty);
        endGameStrategy = new EndGameStrategyOr(endGameStrategies);
    }

    private void setUp() {
        setInitialCards();
        discardPile = new DiscardPile(initialCards);
        deck = new Deck(discardPile);
        hand = new Hand();
        play = new Play();
        setInitialBuyDecks();
        turn = new Turn(newTurnStatus, discardPile, deck, hand, play, buyDecks);
        setEndGameStrategy();
        game = new Game(turn, endGameStrategy);
    }

    @Test
    public void simulateTurnTest() {
        setUp();
        assertEquals(hand.getSize(), 5);

        while (hand.lookAt(0).isPresent()) {
            assertTrue(game.playCard(0));
        }

        assertTrue(game.endPlayCardPhase());

        int coins = turn.getCurrentTurnStatus().getCoins();
        if(coins >= 5) {
            assertTrue(game.buyCard(GameCardType.GAME_CARD_TYPE_MARKET));
            assertEquals(coins-5, turn.getCurrentTurnStatus().getCoins());
            assertEquals(0, turn.getCurrentTurnStatus().getBuys());
            assertEquals(deck.getSize() + hand.getSize() + discardPile.getSize() + play.getSize(), 11);
        }
        else assertFalse(game.buyCard(GameCardType.GAME_CARD_TYPE_MARKET));

        assertTrue(game.endTurn());
        assertEquals(hand.getSize(), 5);
        assertEquals(play.throwAll().size(), 0);
    }

    @Test
    public void endGameProvinceCardsTest() {
        setUp();
        assertTrue(game.endPlayCardPhase());
        turn.getCurrentTurnStatus().setCoins(1000);
        turn.getCurrentTurnStatus().setBuys(10);
        for (int i=0; i<10; i++) assertTrue(game.buyCard(GameCardType.GAME_CARD_TYPE_PROVINCE));
        assertFalse(game.endTurn());
        assertTrue(game.isGameOver());
    }

    @Test
    public void endGame3DecksTest() {
        setUp();
        assertTrue(game.endPlayCardPhase());
        turn.getCurrentTurnStatus().setCoins(1000);
        turn.getCurrentTurnStatus().setBuys(10);
        for (int i=0; i<10; i++) assertTrue(game.buyCard(GameCardType.GAME_CARD_TYPE_MARKET));
        assertTrue(game.endTurn());
        assertFalse(game.isGameOver());
        assertTrue(game.endPlayCardPhase());
        turn.getCurrentTurnStatus().setCoins(1000);
        turn.getCurrentTurnStatus().setBuys(10);
        for (int i=0; i<10; i++) assertTrue(game.buyCard(GameCardType.GAME_CARD_TYPE_VILLAGE));
        assertTrue(game.endTurn());
        assertFalse(game.isGameOver());
        assertTrue(game.endPlayCardPhase());
        turn.getCurrentTurnStatus().setCoins(1000);
        turn.getCurrentTurnStatus().setBuys(10);
        for (int i=0; i<10; i++) assertTrue(game.buyCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));
        assertTrue(game.isGameOver());
        assertFalse(game.endPlayCardPhase());
    }
}
