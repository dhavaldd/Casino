package BlackJack;

import java.security.SecureRandom;
import java.util.Random;

public class Deck {
    
	private static final int NO_OF_CARDS = Card.NO_OF_RANKS * Card.NO_OF_SUITS;
	public Card[] cards;
	private int nextCardIndex = 0;
	private Random random = new SecureRandom();

	public Deck() {
        cards = new Card[NO_OF_CARDS];
        int index = 0;
        for (int suit = Card.NO_OF_SUITS - 1; suit >= 0; suit--) {
            for (int rank = Card.NO_OF_RANKS - 1; rank >= 0 ; rank--) {
                cards[index++] = new Card(rank, suit);
            }
        }
       
    }
    
	public void shuffle() {
        for (int oldIndex = 0; oldIndex < NO_OF_CARDS; oldIndex++) {
            int newIndex = random.nextInt(NO_OF_CARDS);
            Card tempCard = cards[oldIndex];
            cards[oldIndex] = cards[newIndex];
            cards[newIndex] = tempCard;
        }
        nextCardIndex = 0;
    }
}
