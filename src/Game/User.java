package Game;
 
public class User {
    private String name;          // Name of the user
    private int id;               // ID of the user
    private Deck hand;            // Deck representing the user's hand of cards
    private boolean isHuman;      // Flag indicating if the user is a human or not
 
    // Constructor to initialize the User object with name, id, hand, and isHuman flag
    public User(String name, int id, boolean isHuman) {
        this.name = name;
        this.id = id;
        this.hand = new Deck();   // Initialize hand as a new Deck
        this.isHuman = isHuman;
    }
 
    // Getter for name
    public String getName() {
        return name;
    }
 
    // Setter for name
    public void setName(String name) {
        this.name = name;
    }
 
    // Getter for id
    public int getId() {
        return id;
    }
 
    // Setter for id
    public void setId(int id) {
        this.id = id;
    }
 
    // Method to add a card to the user's hand
    public void addCardToHand(Card card) {
        hand.addCard(card);
    }
 
    // Method to play (draw) a card from the user's hand
    public Card playCard() {
        return hand.drawCard();
    }
 
    // Method to check if the user has a deck and it is not empty
    public boolean hasDeck() {
        return hand != null && !hand.isEmpty();
    }
 
    // Method to get the count of cards in the user's hand
    public int getCardCount() {
        return hand.size();
    }
 
    // Getter for hand (Deck)
    public Deck getHand() {
        return hand;
    }
 
    // Method to check if the user is a human
    public boolean isHuman() {
        return isHuman;
    }
 
    // Override toString to provide a string representation of the User object
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", hand=" + hand +
                ", isHuman=" + isHuman +
                '}';
    }
}
