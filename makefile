JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class: 
	$(JC) $(JFLAGS) $*.java

CLASSES = Logging.java Hand.java Deck.java Player.java Dealer.java Game.java

default: classes

classes: $(CLASSES:.java=.class)

clean: 
	$(RM) *.class
cleanCsv:
	$(RM) *.csv
cleancsv:
	$(RM) *.csv
