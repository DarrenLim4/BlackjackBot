
/**
 * This class represents the dealer in a game of blackjack
 * 
 * @since Fall 2023
 * @author Michael Plekan
 */
public class Dealer {
    private static final int HIT = 0;
    private static final int STD = 1;
    private static final int DD = 2;
    public Hand hand;

    /**
     * This function creates a new dealer
     */
    public Dealer() {
        this.hand = new Hand();
    }
    public Dealer(Deck deck) {
        this.hand = new Hand(deck.draw(), deck.draw());
    }
    
    /**
     * This function handles the result of the decide function
     * 
     * @param action The action to be taken
     * @param player The player to be acted upon
     * @param deck The deck to be drawn from
     */
    public void handle(int action, Player player,int handIndex, Deck deck) {
        Logging.logToGroup("game", player.hands.get(handIndex)+"",action==0?"HIT":action==1?"STD":"DD");
        // handle the action
        switch (action) {
            case HIT:
                player.hands.get(handIndex).addCard(deck.draw());
                break;
            case STD:
                break;
            case DD:
                player.hands.get(handIndex).addCard(deck.draw());
                player.bet *= 2;
                break;
            default:
                Logging.logToGroup("error", "Invalid action: " + action);// change to log to error group
                System.exit(1);
                break;
        }
    }
        
    /**
     * This function decides what to do based on the current hand
     * 
     * @param deck The deck to be drawn from
     */
    public int decide(Deck deck) {
        // decide what to do
        // Dealer hit on soft 17 and below, stand on hard 17 and above

        if (hand.isSoft()) {
            if (hand.getValue() <= 17) {
                //if bust, then try the soft value
                if(hand.getValue() > 21 && hand.getSoftValue() <= 17) {
                    hand.addCard(deck.draw());
                    return HIT;
                } 
                //stand
                return STD;
                
            } 
        } 
        if (hand.getValue() < 17) {
            hand.addCard(deck.draw());
            return HIT;
        }
        //stand
        return STD;
    
    }
}