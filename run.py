import numpy as np
from enum import Enum

global numofcards
class Cards(Enum):
    A = {'card': 1, 'value': [1,11]}
    TWO = {'card': 2, 'value': [2]}
    THREE = {'card': 3, 'value': [3]}
    FOUR = {'card': 4, 'value': [4]}
    FIVE = {'card': 5, 'value': [5]}
    SIX = {'card': 6, 'value': [6]}
    SEVEN = {'card': 7, 'value': [7]}
    EIGHT = {'card': 8, 'value': [8]}
    NINE = {'card': 9, 'value': [9]}
    TEN = {'card': 10, 'value': [10]}
    J = {'card': 11, 'value': [10]}
    Q = {'card': 12, 'value': [10]}
    K = {'card': 13, 'value': [10]}


def __getitem__(self, item):
    return self._data['value']

def cards(card):
    #match the card name string to the enum
    return [Cards[x.upper()] for x in card] if isinstance(card, list) else Cards[card.upper()]
#input number of decks being played on the game
while True:
    numofdecks = int(input("Number of decks being used on this game: ")) 
    if numofdecks in [1,2,4,6,8]:
        break


deck = {
    Cards.A: 4*numofdecks,
    Cards.TWO: 4*numofdecks,
    Cards.THREE: 4*numofdecks,
    Cards.FOUR: 4*numofdecks,
    Cards.FIVE: 4*numofdecks,
    Cards.SIX: 4*numofdecks,
    Cards.SEVEN: 4*numofdecks,
    Cards.EIGHT: 4*numofdecks,
    Cards.NINE: 4*numofdecks,
    Cards.TEN: 4*numofdecks,
    Cards.J: 4*numofdecks,
    Cards.Q: 4*numofdecks,
    Cards.K: 4*numofdecks
}


#number of cards in the deck
numofcards = numofdecks * 52
#running count
count = 0
that=0
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
def cardcount(dealt,cnt):
    print(dealt)
    decksLeft=0
    if numofcards <= 52:
        decksLeft = 1
    else:
        decksLeft = int((numofcards / 52))
    print(f"Start:{cnt,decksLeft}")
    if dealt == Cards.TWO or dealt == Cards.SEVEN:
        cnt += 0.5
    elif dealt == Cards.THREE or dealt == Cards.FOUR or dealt == Cards.SIX:
        cnt += 1
    elif dealt == Cards.FIVE:
        cnt += 1.5
    elif dealt == Cards.NINE:
        cnt -= 0.5
    elif dealt == Cards.A or dealt == Cards.TEN or dealt == Cards.J or dealt == Cards.Q or dealt == Cards.K:
        cnt -= 1
    print(f"End:{cnt,decksLeft}")
    return float(cnt)/float(decksLeft),cnt

def dothathing():
    total=0
    for x in deck:
        if x.value["value"][0] <= 21-yourcount:
            print(f"| {x.name} |", end="")
            total+=deck[x]
    print(f"Total number of viable Cards left {total}")

#update the deck with the cards drawn
def updatedeck(cards,numcards):
    CRED = '\033[91m'
    CEND = '\033[0m'
    if isinstance(cards, list):
        numcards-=len(cards)
        for x in cards:
            deck[x]-=1
            if deck[x]<0:print(f"{CRED}WARNING: {x.name} is less than 0{CEND}")
    else:
        numcards-=1
        deck[cards]-=1
        if deck[cards]<0:print(f"{CRED}WARNING: {cards.name} is less than 0{CEND}")
        
    print(f"Number of Cards left {numcards}")

    return numcards
    

while True: #Round Loop
    while True: #input error check
        temp = input("Initial User Cards: ").split()
        if(all(x in [member.name for member in Cards] for x in temp)):
            yourhand=cards(temp)
            break
    numofcards=updatedeck(yourhand,numofcards)
    for x in yourhand:
        that,count=cardcount(x,count)
    yourcount=sum([x.value["value"][0] for x in yourhand])
    dothathing()
    while True:   #input error check
        temp= input("Initial Dealer Card: ")
        if temp in [member.name for member in Cards]:
            dealershand=cards(temp)
            break

    numofcards=updatedeck(dealershand,numofcards)
    that,count=cardcount(dealershand,count)
    dealercount=dealershand.value["value"][0]
    other=[]
    while True:   #input error check
        temp = input("Other Cards: ").split()
        if(all(x in [member.name for member in Cards] for x in temp)):
            other=cards(temp)
            break
    
    numofcards=updatedeck(other,numofcards)
    for x in other:
        that,count=cardcount(x,count)
    print(f"The advantage we have is {that}")
    # cardcount(dealt)