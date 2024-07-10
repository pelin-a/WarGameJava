
package Game;

import java.util.List;

public class User {

   private String name;
   private int id;
   private Deck hand;
   private boolean isHuman;
   	
   public User(String name, int id, boolean isHuman) {
 		
       this.name = name;
       this.id = id;
       this.hand = new Deck();
       this.isHuman = isHuman;
   }

   public String getName() {

       return name;

   }

   public void setName(String name) {

       this.name = name;

   }

   public int getId() {

       return id;

   }

   public void setId(int id) {
	   this.id = id;

   }

   public Deck getHand() {

       return hand;

   }

   public void setHand(Deck hand) {

       this.hand = hand;

   }
   public void addCardsToHand(List<Card> deck) {
	   hand.addCards(deck);
   }

   public void addCardToHand(Card card) {

       hand.addCard(card);

   }

  /* public void removeCardFromHand(Card card) {

       hand.removeCard(card);

   }*/

   public Card playCard() {

       if (!hand.isEmpty()) {

           return hand.drawCard();

       }

       return null;

   }

   public boolean hasDeck() {

       return hand != null && !hand.isEmpty();

   }

   public int getCardCount() {

       return hand.size();

   }

   public boolean isHuman() {

       return isHuman;

   }

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
 