package BlackJack;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Card implements Comparable<Card>{
	
	private final int rank;
	private final int suit;
	public  ArrayList<ImageIcon> slotImage = new ArrayList<ImageIcon>();
	
	public static final int NO_OF_SUITS = 4;
	public static final int NO_OF_RANKS = 13;
	
	public static final int SPADES   = 3;
    public static final int HEARTS   = 2;
    public static final int CLUBS    = 1;
    public static final int DIAMONDS = 0;
    
    public static final int ACE      = 12;
    public static final int KING     = 11;
    public static final int QUEEN    = 10;
    public static final int JACK     = 9;
    public static final int TEN      = 8;
    public static final int NINE     = 7;
    public static final int EIGHT    = 6;
    public static final int SEVEN    = 5;
    public static final int SIX      = 4;
    public static final int FIVE     = 3;
    public static final int FOUR     = 2;
    public static final int THREE    = 1;
    public static final int DEUCE    = 0;
    
    public static final String[] RANK_SYMBOLS = {
        "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"
    };
    
    public static final char[] SUIT_SYMBOLS = { 'd', 'c', 'h', 's' };

    public Card(int rank , int suit)
    {
    	if(!(rank >= 0 && rank < NO_OF_RANKS))
    		throw new IllegalArgumentException("Rank Not Valid");

    	if(!(suit >= 0 && suit < NO_OF_SUITS))
    		throw new IllegalArgumentException("Suit Not Valid");

    	this.rank = rank;
    	this.suit = suit;
    	String fileName = RANK_SYMBOLS[rank]+SUIT_SYMBOLS[suit];
    	this.slotImage.add(createImageIcon("images/"+fileName+".jpg",fileName));
    }
    
    public ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
            } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

	public Card(String s) 
    {	
        if (s == null) {
            throw new IllegalArgumentException("Null String");
        }
        s = s.trim();
        if (s.length() != 2) {
            throw new IllegalArgumentException("Invalid length");
        }
        
        String rankSymbol = s.charAt(0) +"";
        char suitSymbol = s.charAt(1);
        int rank = -1;
        for (int i = 0; i < Card.NO_OF_RANKS; i++) {
            if (rankSymbol.equals(RANK_SYMBOLS[i])) {
                rank = i;
                break;
            }
        }
        if (rank == -1) {
            throw new IllegalArgumentException("Unknown rank: " + rankSymbol);
        }
        
        int suit = -1;
        for (int i = 0; i < Card.NO_OF_SUITS; i++) {
            if (suitSymbol == SUIT_SYMBOLS[i]) {
                suit = i;
                break;
            }
        }
        if (suit == -1) {
            throw new IllegalArgumentException("Unknown suit: " + suitSymbol);
        }
        
        
        this.rank = rank;
        this.suit = suit;
    }
    
    public int getSuit() {
        return suit;
    }
    
    public int getRank() {
        return rank;
    }
    
    @Override
    public int hashCode() {
        return (rank * NO_OF_SUITS + suit);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            return ((Card) obj).hashCode() == hashCode();
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Card card) {
        int thisValue = hashCode();
        int otherValue = card.hashCode();
        if (thisValue < otherValue) {
            return -1;
        } else if (thisValue > otherValue) {
            return 1;
        } else {
            return 0;
        }
    }
    
    @Override
    public String toString() {
        return RANK_SYMBOLS[rank] + SUIT_SYMBOLS[suit];
    }
}
