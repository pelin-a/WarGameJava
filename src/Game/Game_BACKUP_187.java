<<<<<<< HEAD
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
=======
public class Game {

       public class Game {
    private Label player1Label,  player2Label,  winnerLabel;
    private Button dealButton,  quitButton;
    private Panel player1Panel,  player2Panel;
    private ArrayList<Card> player1Deck = new ArrayList<>(),  player2Deck = new ArrayList<>(),  warDeck = new ArrayList<>();
    private Deck deck; // New instance of the Deck class

    public Game() {
        setTitle("War Card Game");
        
        // Initialize the deck
        deck = new Deck();
        deck.initialize();
        deck.shuffle();

        player1Label = new Label("Player 1");
        player2Label = new Label("Player 2");
        winnerLabel = new Label("");
        dealButton = new Button("Deal");
        quitButton = new Button("Quit");

        Panel topPanel = new Panel();
        topPanel.add(player1Label);
        topPanel.add(player2Label);
        topPanel.add(winnerLabel);

        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(1,  2));

        player1Panel = new Panel();
        player1Panel.setLayout(new BoxLayout(player1Panel,  BoxLayout.Y_AXIS));
        player2Panel = new Panel();
        player2Panel.setLayout(new BoxLayout(player2Panel,  BoxLayout.Y_AXIS));

        centerPanel.add(player1Panel);
        centerPanel.add(player2Panel);

        Panel bottomPanel = new Panel();
        bottomPanel.add(dealButton);
        bottomPanel.add(quitButton);

        add(topPanel,  BorderLayout.NORTH);
        add(centerPanel,  BorderLayout.CENTER);
        add(bottomPanel,  BorderLayout.SOUTH);

        dealButton.addActionListener(e -> dealCards());
        quitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
>>>>>>> origin/main
    }
 }

    public void playRound() {
        if (!player1Deck.isEmpty() && !player2Deck.isEmpty()) {
            Card player1Card = player1Deck.remove(0);
            Card player2Card = player2Deck.remove(0);

            Label player1CardLabel = new Label(player1Card.toString());
            player1CardLabel.setIcon(player1Card.getImageIcon());
            player1Panel.add(player1CardLabel);

            Label player2CardLabel = new Label(player2Card.toString());
            player2CardLabel.setIcon(player2Card.getImageIcon());
            player2Panel.add(player2CardLabel);

            if (player1Card.getValue() > player2Card.getValue()) {
                player1Deck.add(player1Card);
                player1Deck.add(player2Card);
            } else if (player1Card.getValue() < player2Card.getValue()) {
                player2Deck.add(player1Card);
                player2Deck.add(player2Card);
            } else {
                warDeck.add(player1Card);
                warDeck.add(player2Card);
                war();
            }

            player1Panel.revalidate();
            player2Panel.revalidate();
        }

        if (player1Deck.isEmpty()) {
            winnerLabel.setText("Player 2 wins!");
        } else if (player2Deck.isEmpty()) {
            winnerLabel.setText("Player 1 wins!");
        }
    }

    public void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("game.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player1Deck);
            out.writeObject(player2Deck);
            out.writeObject(warDeck);
            out.close();
            fileOut.close();
            System.out.println("Game saved successfully.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("game.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            player1Deck = (ArrayList<Card>) in.readObject();
            player2Deck = (ArrayList<Card>) in.readObject();
            warDeck = (ArrayList<Card>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Game loaded successfully.");
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Game class not found.");
            c.printStackTrace();
            return;
        }
        player1Panel.removeAll();
        player2Panel.removeAll();
        winnerLabel.setText("");
        for (Card card : player1Deck) {
            Label cardLabel = new Label(card.toString());
            cardLabel.setIcon(card.getImageIcon());
            player1Panel.add(cardLabel);
        }
        for (Card card : player2Deck) {
            Label cardLabel = new Label(card.toString());
            cardLabel.setIcon(card.getImageIcon());
            player2Panel.add(cardLabel);
        }
        player1Panel.revalidate();
        player2Panel.revalidate();
    }

    public void exitGame() {
        System.exit(0);
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

<<<<<<< HEAD
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
=======
    private void war() {
        if (player1Deck.size() < 3 || player2Deck.size() < 3) {
            // Handle the case where either player doesn't have enough cards for war
            // You can choose to either end the game or replay the game from the beginning
            // For simplicity, let's end the game here
            if (player1Deck.size() < 3) {
                winnerLabel.setText("Player 2 wins due to insufficient cards for war!");
            } else if (player2Deck.size() < 3) {
                winnerLabel.setText("Player 1 wins due to insufficient cards for war!");
            }
            return;
        }

        // Remove the top 3 cards from each player's deck
        Card player1Card1 = player1Deck.remove(0);
        Card player1Card2 = player1Deck.remove(0);
        Card player1Card3 = player1Deck.remove(0);
        Card player2Card1 = player2Deck.remove(0);
        Card player2Card2 = player2Deck.remove(0);
        Card player2Card3 = player2Deck.remove(0);

        // Add the removed cards to the war deck
        warDeck.add(player1Card1);
        warDeck.add(player1Card2);
        warDeck.add(player1Card3);
        warDeck.add(player2Card1);
        warDeck.add(player2Card2);
        warDeck.add(player2Card3);

        // Display the face-up cards
        Label player1CardLabel1 = new Label(player1Card1.toString());
        player1CardLabel1.setIcon(player1Card1.getImageIcon());
        player1Panel.add(player1CardLabel1);

        Label player1CardLabel2 = new Label(player1Card2.toString());
        player1CardLabel2.setIcon(player1Card2.getImageIcon());
        player1Panel.add(player1CardLabel2);

        Label player1CardLabel3 = new Label(player1Card3.toString());
        player1CardLabel3.setIcon(player1Card3.getImageIcon());
        player1Panel.add(player1CardLabel3);

        Label player2CardLabel1 = new Label(player2Card1.toString());
        player2CardLabel1.setIcon(player2Card1.getImageIcon());
        player2Panel.add(player2CardLabel1);

        Label player2CardLabel2 = new Label(player2Card2.toString());
        player2CardLabel2.setIcon(player2Card2.getImageIcon());
        player2Panel.add(player2CardLabel2);

        Label player2CardLabel3 = new Label(player2Card3.toString());
        player2CardLabel3.setIcon(player2Card3.getImageIcon());
        player2Panel.add(player2CardLabel3);

        player1Panel.revalidate();
        player2Panel.revalidate();

        // Compare the face-up cards
        if (player1Card3.getValue() > player2Card3.getValue()) {
            player1Deck.addAll(warDeck);
            warDeck.clear();
        } else if (player1Card3.getValue() < player2Card3.getValue()) {
            player2Deck.addAll(warDeck);
            warDeck.clear();
        } else {
            // Recursively call war() until a winner is determined
            war();
        }
    }
}
>>>>>>> origin/main
