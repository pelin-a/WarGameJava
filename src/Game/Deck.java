package Game;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Deck {
	//TODO: createDeck, distributeDeck, shuffleDeck, revealCard
	private List<Card> deck;
	
	//Constructor constructs an empty deck called deck which is an ArrayList.
	public Deck() {
		this.deck = new ArrayList<>();
	
	}
	
	//Adds deck to the deck
	public void addCard(Card card) {
		this.deck.add(card);
	}
	//Adds a list of deck to the deck.
	public void addCards(List<Card> deck) {
		this.deck.addAll(deck);
	}
	//Creates a deck with all the necessary deck in them
	public void initialize() {
		String[] suits= {"Clubs","Diamonds","Hearts","Spades"};
		String[] values ={"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
		
		for(String suit:suits) {
			for(String value: values) {
				Card card= new Card(suit,value);
				this.addCard(card);
			}
		}
	}
	//returns the deck object.
	public List<Card> getDeck(){
		return this.deck;
	}	
	//shuffles the deck using shuffle method from Collections class.
	public void shuffle() {
        Collections.shuffle(deck);
    }
	//prints deck info card by card.
	public void printDeck() {
		String str="";
		for(Card card : deck) {
			str+=card.getValue()+" of "+card.getSuit()+"\n";
		}
		System.out.println(str);
	}
	//sets deck according to the given parameter that is a list of cards.
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	
	
	
	
	
	
	
	
	


}
