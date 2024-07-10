
package Game;  // Declares the package name.
import java.util.List;

import java.io.*;  // Imports classes from the java.io package for handling input and output operations.
 
public class Save extends Game {  // Declares the Save class which extends the Game class.

    private User[] users;  // Declares a private array of User objects to store the users.
    private Deck deck;  // Declares a private Deck object to store the deck of cards.
 
    public Save(User[] users, Deck deck) {  // Constructor for the Save class, initializing users and deck.

        this.users = users;  // Assigns the input users array to the class's users variable.
        this.deck = deck;  // Assigns the input deck to the class's deck variable.

    }
 
    public void saveGame(String filename) {  // Method to save the game state to a file.

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {  // Tries to create a BufferedWriter for the specified file. A BufferWriter is a tool that temporarily stores data in a buffer to optimize and reduce the no of write operations to an output destination.

            for (User user : users) {  // Loops through each user.

                writer.write(user.getName());  // Writes the user's name to the file.

                writer.newLine();  // Adds a new line after the user's name.

                for (Card card : user.getHand()) {  // Loops through each card in the user's hand.

                    writer.write(card.getSuit() + "," + card.getValue());  // Writes the card's suit and value separated by a comma.

                    writer.newLine();  // Adds a new line after each card.

                }

                writer.write("END");  // Writes "END" to mark the end of the user's cards.

                writer.newLine();  // Adds a new line after "END".

            }

            for (Card card : deck.getDeck()) {  // Loops through each card in the deck.

                writer.write(card.getSuit() + "," + card.getValue());  // Writes the card's suit and value separated by a comma.

                writer.newLine();  // Adds a new line after each card.

            }

            System.out.println("Game saved successfully.");  // Prints a success message to the screen.

        } catch (IOException e) {  // Catches any IOException that might occur. An IOException is an error that occurs when the game can't read or write data properly.

            System.err.println("Error saving game: " + e.getMessage());  // Prints an error message to the screen.

        }

    }
 
    public void loadGame(String filename) {  // Method to load the game state from a file.

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {  // Tries to create a BufferedReader for the specified file.

            String line;  // Declares a string variable to store each line read from the file.

            int userIndex = 0;  

 User user = null; // Initializes a variable to hold the current user being processed, starting with null.
 
            while ((line = reader.readLine()) != null) { // Reads lines from the reader until there are no more lines.
                if (line.equals("END")) { // Checks if the current line indicates the end of a user's section.
                    if (user != null) { // Checks if a user has been created.
                        users[userIndex++] = user; // Adds the user to the users array and raises the index.
                        user = null; // Resets the previous user for a clean variable for the next user.
                    }
                } else if (line.contains(",")) { // Check if the line contains a comma, indicating card info.
                    String[] parts = line.split(","); // Split the line into parts based on the comma places.
                    Card card = new Card(parts[0], Integer.parseInt(parts[1])); // Create a new Card object with the parts.
                    if (user != null) { // If a user is currently being processed.
                        user.addCardToHand(card); // Add the card to the user's hand.
                    } else { // If no user is being processed...
                        deck.addCard(card); // Add the card to the deck GIRL.
                    }
                } else { // If the line doesn't contain a comma or 'END' it is a user's name.
                    user = new User(line); // Create a new User object with the name from the line.
                }
            }
            System.out.println("Game loaded successfully."); // Print a msg indicating the game was loaded successfullyyy.
        } catch (IOException e) { // Catch any errors that occur during the reading process.
            System.err.println("Error loading game: " + e.getMessage()); //Print an error msg with the exception details.
        }
    }
 
    @Override
    public String toString() { // Overrides the toString method to provide a string representation of the Save object.
        StringBuilder sb = new StringBuilder("Save{users=[");// Creates a StringBuilder in order to build the string obviously.
        for (User user : users) { // Loops the users array in order to look at each user one by one.
            sb.append(user.toString()).append(", "); // Adds each user's string representation to the StringBuilder.
        }
        sb.append("], deck=").append(deck).append('}'); // Adds the deck's string representation and closes the string.
        return sb.toString(); // Returns the complete string.
    }
}
 