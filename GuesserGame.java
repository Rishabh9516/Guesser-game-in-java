import java.util.Scanner;

class Guesser {
    private int guessNumber;

    public int guessNumber(Scanner scanner) {
        System.out.print("Guesser, enter a number: ");
        System.out.flush(); // Ensure output is displayed before input
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a valid number: ");
            scanner.next();
        }
        guessNumber = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline
        return guessNumber;
    }
}

class Player {
    private int playerGuess;

    public int guessNumber(Scanner scanner, int playerId) {
        System.out.print("Player " + playerId + ", enter your guess: ");
        System.out.flush();
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a valid number: ");
            scanner.next();
        }
        playerGuess = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline
        return playerGuess;
    }
}

class Umpire {
    private int guesserNumber;
    private int[] playerGuesses;
    private int playerCount;

    public Umpire(int playerCount) {
        this.playerCount = playerCount;
        playerGuesses = new int[playerCount];
    }

    public void collectNumberFromGuesser(Scanner scanner) {
        Guesser guesser = new Guesser();
        guesserNumber = guesser.guessNumber(scanner);
    }

    public void collectNumbersFromPlayers(Scanner scanner) {
        for (int i = 0; i < playerCount; i++) {
            Player player = new Player();
            playerGuesses[i] = player.guessNumber(scanner, i + 1);
        }
    }

    public void compareGuesses() {
        boolean hasWinner = false;
        System.out.print("Winner(s): ");
        for (int i = 0; i < playerCount; i++) {
            if (playerGuesses[i] == guesserNumber) {
                System.out.print("Player " + (i + 1) + " ");
                hasWinner = true;
            }
        }

        if (!hasWinner) {
            System.out.println("No one won. All players lost.");
        } else {
            System.out.println("won the game!");
        }
    }
}

public class GuesserGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---- Welcome to the Guesser Game! ----");
        System.out.print("Enter the number of players: ");
        System.out.flush();
        int playerCount;
        while (!scanner.hasNextInt() || (playerCount = scanner.nextInt()) <= 0) {
            System.out.print("Invalid input. Enter a positive number: ");
            scanner.next();
        }
        scanner.nextLine(); // Consume leftover newline

        Umpire umpire = new Umpire(playerCount);
        umpire.collectNumberFromGuesser(scanner);
        umpire.collectNumbersFromPlayers(scanner);
        umpire.compareGuesses();

        System.out.println("----- GAME OVER -----");
        scanner.close();
    }
}
