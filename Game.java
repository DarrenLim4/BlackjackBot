import java.util.ArrayList;

public class Game{
    public static void main(String[] args){
        ArrayList<Player> players = new ArrayList<Player>();
        Dealer dealer = new Dealer();
        Deck deck = new Deck(1);

        //get input from args and create players
        for(int i = 0; i < Integer.parseInt(args[0]); i++){
            players.add(new Player());
        }

        while(deck.size>(players.size()+1)*5){
            //deal cards
            for(Player player : players){
                player.hands.add(new Hand(deck.draw(), deck.draw()));
            }
            //get bets
            for(Player player : players){
                player.bet();
            }
            //handle each players hand(s)
            for(Player player : players){
                for(int i = 0; i < player.hands.size(); i++){
                    while(player.decide(i, dealer.hand.getCard(0).value) != 0){
                        dealer.handle(player.decide(i, dealer.hand.getCard(0).value), player, i, deck);
                    }
                }
            }
            //handle dealer's hand

            //determine winners
            break;

        }
        System.out.println("Not enough cards in deck to play game"+players);
    }
}
