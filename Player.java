import java.util.ArrayList;

public class Player {
    private static final int HIT = 0;
    private static final int STD = 1;
    private static final int DD = 2;
    // private final int SPT = 3;
    public ArrayList<Hand> hands;
    public int bet = 0;
    public int difference = 0;
    public boolean intialHand = true;

    private final int[][] hardTable = {
            // Dealer's up card from 2 to Ace
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 3
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 4
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 5
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 6
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 7
            { HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT, HIT }, // for value 8
            { HIT, DD, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value 9
            { DD, DD, DD, DD, DD, DD, DD, DD, HIT, HIT }, // for value 10
            { DD, DD, DD, DD, DD, DD, DD, DD, DD, DD }, // for value 11
            { HIT, HIT, STD, STD, STD, HIT, HIT, HIT, HIT, HIT }, // for value 12
            { STD, STD, STD, STD, STD, HIT, HIT, HIT, HIT, HIT }, // for value 13
            { STD, STD, STD, STD, STD, HIT, HIT, HIT, HIT, HIT }, // for value 14
            { STD, STD, STD, STD, STD, HIT, HIT, HIT, HIT, HIT }, // for value 15
            { STD, STD, STD, STD, STD, HIT, HIT, HIT, HIT, HIT }, // for value 16
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value 17
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value 18
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value 19
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value 20
    };

    private final int[][] softTable = {
            { HIT, HIT, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value A,2
            { HIT, HIT, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value A,3
            { HIT, HIT, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value A,4
            { HIT, HIT, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value A,5
            { HIT, HIT, DD, DD, DD, HIT, HIT, HIT, HIT, HIT }, // for value A,6
            { STD, DD, DD, DD, DD, STD, STD, HIT, HIT, HIT }, // for value A,7
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD }, // for value A,8
            { STD, STD, STD, STD, STD, STD, STD, STD, STD, STD } // for value A,9
    };

    public Player() {
        this.hands = new ArrayList<Hand>();
    }

    public int decide(int hand, int DealerUpCard) {
        // decide what to do
        if (intialHand) {
            if (hands.get(hand).isSoft()) {
                return softTable[hands.get(hand).getValue()][DealerUpCard + 2];
            } else {
                return hardTable[hands.get(hand).getValue()][DealerUpCard + 2];
            }
        } else {
            if (hands.get(hand).isSoft()) {
                return softTable[hands.get(hand).getSoftValue()][DealerUpCard + 2] == DD ? HIT
                        : softTable[hands.get(hand).getSoftValue()][DealerUpCard + 2];
            } else {
                return hardTable[hands.get(hand).getValue()][DealerUpCard + 2] == DD ? HIT
                        : hardTable[hands.get(hand).getValue()][DealerUpCard + 2];
            }
        }
    }

    public int bet() {
        // return the amount of money to bet
        bet = 2;
        return 2;
    }

    public void split() {
        // decide if you want to split
        if (hands.get(0).getValue() == 22) {
            // split
            hands.add(new Hand(new Card[] {
                    hands.get(0).hand.get(1)
            }));

            hands.get(0).hand.remove(1);
        }
    }
    @Override
    public String toString() {
        return "Player: " + hands;
    }
}