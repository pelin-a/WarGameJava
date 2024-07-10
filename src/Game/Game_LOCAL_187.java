import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents the Game of War
public class Game {
    private User player1; // First player
    private User player2; // Second player
    private Deck deck; // Deck of cards
    private boolean isGameOver; // Game over flag

    // Constructor to initialize the game with two players and a deck of cards
    public Game(User player1, User player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.deck = new Deck();
        this.isGameOver = false;
        initializeGame();
    }

    // Initializes the game by shuffling and distributing cards
    private void initializeGame() {
        deck.shuffle(); // Shuffle the deck
        dealCards(); // Distribute cards to players
    }

    // Deals cards evenly between the two players
    private void dealCards() {
        List<Card> cards = deck.getDeck();
        for (int i = 0; i < cards.size(); i++) {
            if (i % 2 == 0) {
                player1.addCardToHand(cards.get(i));
            } else {
                player2.addCardToHand(cards.get(i));
            }
        }
    }

    // Main method to start the game
    public void startGame() {
        while (!isGameOver) {
            playRound(); // Play a round of the game
            isGameOver = checkGameOver(); // Check if the game is over
        }
    }

    // Logic for playing a single round
    public void playRound() {
        if (player1.hasDeck() && player2.hasDeck()) {
            Card card1 = player1.playCard();
            Card card2 = player2.playCard();
            System.out.println(player1.getName() + " plays " + card1);
            System.out.println(player2.getName() + " plays " + card2);

            if (card1.getRank() > card2.getRank()) {
                player1.winCards(card1, card2); // Player 1 wins the round
            } else if (card1.getValue() < card2.getValue()) {
                player2.winCards(card1, card2); // Player 2 wins the round
            } else {
                handleWar(card1, card2); // Handle war scenario
            }
        }
    }

    // Handles the "war" scenario when both players play cards of equal value
    private void handleWar(Card card1, Card card2) {
        System.out.println("War!");
        List<Card> warPile = new ArrayList<>();
        warPile.add(card1);
        warPile.add(card2);

        while (true) {
            if (player1.hasCards() && player2.hasCards()) {
                warPile.add(player1.playCard());
                warPile.add(player2.playCard());

                if (!player1.hasCards() || !player2.hasCards()) {
                    break;
                }

                Card warCard1 = player1.playCard();
                Card warCard2 = player2.playCard();
                warPile.add(warCard1);
                warPile.add(warCard2);

                if (warCard1.getValue() > warCard2.getValue()) {
                    player1.winCards(warPile);
                    break;
                } else if (warCard1.getValue() < warCard2.getValue()) {
                    player2.winCards(warPile);
                    break;
                }
            } else {
                break;
            }
        }
    }

    // Checks if the game is over
    private boolean checkGameOver() {
        if (!player1.hasCards()) {
            System.out.println(player2.getName() + " wins the game!");
            return true;
        } else if (!player2.hasCards()) {
            System.out.println(player1.getName() + " wins the game!");
            return true;
        }
        return false;
    }

    // Saves the game state (placeholder)
    public void saveGame() {
        // Logic to save the game state
    }

    // Loads a previously saved game state (placeholder)
    public void loadGame() {
        // Logic to load the game state
    }

    // Displays the current status of the game
    public void displayStatus() {
        System.out.println(player1.getName() + " has " + player1.getCardCount() + " cards.");
        System.out.println(player2.getName() + " has " + player2.getCardCount() + " cards.");
    }

    // Returns player1
    public User getPlayer1() {
        return player1;
    }

    // Returns player2
    public User getPlayer2() {
        return player2;
    }
}