import java.util.ArrayList;

/**
 * Represents a hand of cards.
 * 
 * @since Fall 2023
 * @author Chris Sorros and Michael Plekan
 */
public class Hand {
    public ArrayList<Card> hand;

    /**
     * Constructs a hand with no cards.
     */
    public Hand() {
        this.hand = new ArrayList<Card>();
    }

    /**
     * Constructs a hand with the given cards.
     * 
     * @param cards the cards to add to the hand
     */
    public Hand(Card ...cards) {
        this.hand = new ArrayList<Card>();

        // Add the cards to the hand
        for (Card card : cards) {
            this.hand.add(card);
        }
    }

    /**
     * Returns the card at the given index.
     * 
     * @param index the index of the card to get.
     * 
     * @return the card at the given index.
     */
    public Card getCard(int index){
        return hand.get(index);
    }
    /**
     * Adds a card to the hand.
     * 
     * @param card the card to add
     */
    public void addCard(Card card) {
        this.hand.add(card);
    }

    /**
     * Returns the value of the hand.
     * 
     * @return the value of the hand.
     */
    public int getValue() {
        int value = 0;
        for (Card card : hand) {
            value += card.value;
        }
        return value;
    }

    /**
     * Returns the soft value of the hand.
     * 
     * @return the soft value of the hand
     */
    public int getSoftValue() {
        int value = 0;
        for (Card card : hand) {
            // If the card is an ace, add 1 instead of 11
            value += card.value != 11 ? card.value : 1;
        }
        return value;
    }

    /**
     * Returns if the hand is a bust.
     * 
     * @return if the hand is a bust
     */
    public boolean isBust() {
        return getValue() > 21;
    }

    /**
     * Returns if the hand is a blackjack.
     * 
     * @return if the hand is a blackjack
     */
    public boolean isBlackjack() {
        return getValue() == 21;
    }

    /**
     * Returns if the hand is a soft hand.
     * 
     * @return if the hand is a soft hand.
     */
    public boolean isSoft() {
        //check each card in the hand to see if it is an ace
        //if it is an ace, return true
        //if not, return false
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).name.equals("Ace")) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns if the hand is a pair.
     * 
     * @return if the hand is a pair.
     */
    public boolean isPair() {
        if (hand.get(0).value == hand.get(1).value) {
            return true;
        }
        return false;
    }
    /**
     * Returns the string representation of the hand.
     * 
     * @return the string representation of the hand
     */
    @Override
    public String toString() {
        return "" + hand;
    }
}