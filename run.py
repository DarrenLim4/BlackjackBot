import numpy as np
from enum import Enum


class Cards(Enum):
    ACE = {'card': 1, 'value': [1, 11]}
    TWO = {'card': 2, 'value': 2}
    THREE = {'card': 3, 'value': 3}
    FOUR = {'card': 4, 'value': 4}
    FIVE = {'card': 5, 'value': 5}
    SIX = {'card': 6, 'value': 6}
    SEVEN = {'card': 7, 'value': 7}
    EIGHT = {'card': 8, 'value': 8}
    NINE = {'card': 9, 'value': 9}
    TEN = {'card': 10, 'value': 10}
    JACK = {'card': 11, 'value': 10}
    QUEEN = {'card': 12, 'value': 10}
    KING = {'card': 13, 'value': 10}
#input number of decks being played on the game
numofdecks = int(input("Number of decks being used on this game: "))

deck = {
    Cards.ACE: 4*numofdecks,
    Cards.TWO: 4*numofdecks,
    Cards.THREE: 4*numofdecks,
    Cards.FOUR: 4*numofdecks,
    Cards.FIVE: 4*numofdecks,
    Cards.SIX: 4*numofdecks,
    Cards.SEVEN: 4*numofdecks,
    Cards.EIGHT: 4*numofdecks,
    Cards.NINE: 4*numofdecks,
    Cards.TEN: 4*numofdecks,
    Cards.JACK: 4*numofdecks,
    Cards.QUEEN: 4*numofdecks,
    Cards.KING: 4*numofdecks
}
#number of cards in the deck
numofcards = numofdecks * 52
#running count
count = 0 
#track cards that were previusly played on the game
yourhand = []
yourcount=0
dealershand = []
dealercount=0

# Start with a count of 0
# When you see an A, K, Q, J, or 10: Subtract 1 from your running count
# When you see a 9: Subtract - 0.5 from your running count
# When you see an 8: Do nothing(Worth 0)
# When you see a 2 or 7: Add 0.5 to your running count
# When you see a 3, 4, 5, or 6: Add 1 to your running count
def cardcount(dealt):
    decksLeft = int(numofcards % 52)
    if dealt == Cards.TWO["card"] or dealt == Cards.SEVEN["card"]:
        count = + 0.5
    elif dealt == Cards.THREE["card"] or dealt == Cards.FOUR["card"] or dealt == Cards.SIX["card"]:
        count = + 1
    elif dealt == Cards.FIVE["card"]:
        count = + 1.5
    elif dealt == Cards.EIGHT["card"]:
        count = + 0
    elif dealt == Cards.NINE["card"]:
        count = - 0.5
    elif dealt == Cards.ACE["card"] or dealt == Cards.TEN["card"] or dealt == Cards.JACK["card"] or dealt == Cards.QUEEN["card"] or dealt == Cards.KING["card"]:
        count = - 1
    return count/decksLeft

def dothathing():
    total= sum([x["value"] for x in deck if deck[x]["value"]>=(21-yourcount)])
    print(total)

    
#create class to tell how many copies of each card there are
# class Deck():

#make dictionary to hold the amount of each Cards there are using the enum



while(True):
    usercards= Cards(input("Initial User Cards: ").split())
    dealercards= Cards(input("Initial Dealer Card: "))
    othercards= Cards(input("Other cards on the table: ").split())
    cardcount(dealt)
    

