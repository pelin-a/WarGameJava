package Game;

public class Card {
	
	private String suit;
	private String value;
	private int rank;
	 //Constructor for cards, if the value is numeric sets the rank to the number, if not the rank is set for "Ace", "King", "Queen", "Jack" accordingly. 
	public Card(String suit, String value) {
		
		this.suit=suit;
		this.value=value;
		if(!value.equals("King")&& !value.equals("Queen")&&!value.equals("Jack")){
			this.rank= Integer.parseInt(value);	
		}
		else {
			if(value.equals("King")) {
				this.rank=13;
			}
			if(value.equals("Queen")) {
				this.rank=12;
			}
			if(value.equals("Jack")) {
				this.rank=11;
			}
			if(value.equals("Ace")) {
				this.rank=14;
			}
		}
		
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	//Displays card, does not return anything.
	public void showCard() {
		String str= "Card: "+this.value+" of"+this.suit;
		System.out.println(str);
	}
	
}
