public class Dealer {
    private static final int HIT = 0;
    private static final int STD = 1;
    private static final int DD = 2;
    public Hand hand;

    private int currenthand = 0;

    public Dealer() {
        this.hands = new ArrayList<Hand>();
    }

    public void handle(int action, Player player, Deck deck) {
        switch (action) {
            case HIT:
                player.hands.get(currenthand).addCard(deck.draw());
                break;
            case STD:
                break;
            case DD:
                player.hands.get(currenthand).addCard(deck.draw());
                player.bet *= 2;
                break;
            default:
                System.err.println("Invalid action: " + action);// change to log to error group
                exit(1);
                break;
        }
    }
}