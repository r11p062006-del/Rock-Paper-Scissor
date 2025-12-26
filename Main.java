
import java.util.Random;
import java.util.Scanner;

public class Main {
    // Game constants
    private static final String[] CHOICES = {"ROCK", "PAPER", "SCISSORS"};
    private static final String[] EMOJIS = {"✊", "✋", "✌️"};
    private static final String[] WIN_PHRASES = {
            "Rock crushes Scissors!",
            "Paper covers Rock!",
            "Scissors cuts Paper!"
    };

    // Game state
    private static int playerWins = 0;
    private static int computerWins = 0;
    private static int ties = 0;
    private static int totalRounds = 0;
    private static final Random random = new Random();

    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String PURPLE = "\u001B[35m";
    private static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepPlaying = true;

        clearScreen();
        showWelcomeScreen();
        showInstructions();

        // Wait for player to start
        System.out.print("\n" + YELLOW + BOLD + "Press Enter to start the game..." + RESET);
        scanner.nextLine();

        while (keepPlaying) {
            clearScreen();
            showScoreBoard();
            showChoices();

            // Get player's choice
            int playerChoice = getPlayerChoice(scanner);

            if (playerChoice == -1) {
                // Player wants to quit
                break;
            }

            // Generate computer's choice
            int computerChoice = random.nextInt(3);

            // Display both choices
            displayChoices(playerChoice, computerChoice);

            // Determine winner
            determineWinner(playerChoice, computerChoice);

            // Ask if player wants to continue
            System.out.print("\n" + CYAN + BOLD + "Play another round? (Y/N): " + RESET);
            String response = scanner.next().toUpperCase();
            scanner.nextLine(); // Clear buffer

            if (!response.equals("Y")) {
                keepPlaying = false;
            }
        }

        // Game over - show final results
        showFinalResults();
        System.out.println("\n" + GREEN + BOLD + "Thanks for playing! Goodbye!" + RESET);
        scanner.close();
    }

    // Clear the console screen
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Show welcome screen
    private static void showWelcomeScreen() {
        System.out.println(CYAN + "╔════════════════════════════════════════════════════╗" + RESET);
        System.out.println(YELLOW + BOLD + "        🎮 ROCK PAPER SCISSORS GAME 🎮" + RESET);
        System.out.println(CYAN + "╚════════════════════════════════════════════════════╝\n" + RESET);
    }

    // Show game instructions
    private static void showInstructions() {
        System.out.println(PURPLE + BOLD + "📖 HOW TO PLAY:\n" + RESET);
        System.out.println(YELLOW + "• Choose Rock, Paper, or Scissors to beat the computer" + RESET);
        System.out.println(YELLOW + "• The computer will randomly select its choice" + RESET);
        System.out.println(YELLOW + "• First to win multiple rounds wins the game!\n" + RESET);

        System.out.println(PURPLE + BOLD + "🏆 WINNING RULES:\n" + RESET);
        System.out.println(GREEN + "  Rock (✊) beats Scissors (✌️)" + RESET);
        System.out.println(GREEN + "  Paper (✋) beats Rock (✊)" + RESET);
        System.out.println(GREEN + "  Scissors (✌️) beats Paper (✋)\n" + RESET);

        System.out.println(PURPLE + BOLD + "🎯 QUICK CONTROLS:\n" + RESET);
        System.out.println("  " + GREEN + "R" + RESET + " or " + GREEN + "1" + RESET + " for " + BOLD + "Rock ✊" + RESET);
        System.out.println("  " + GREEN + "P" + RESET + " or " + GREEN + "2" + RESET + " for " + BOLD + "Paper ✋" + RESET);
        System.out.println("  " + GREEN + "S" + RESET + " or " + GREEN + "3" + RESET + " for " + BOLD + "Scissors ✌️" + RESET);
        System.out.println("  " + RED + "Q" + RESET + " to quit the game\n");
    }

    // Show current score board
    private static void showScoreBoard() {
        System.out.println(PURPLE + "════════════════════════════════════════════════════" + RESET);
        System.out.println(CYAN + BOLD + "                    SCORE BOARD" + RESET);
        System.out.println(PURPLE + "════════════════════════════════════════════════════" + RESET);

        System.out.printf("  " + GREEN + "Player Wins: %d" + RESET + "   " +
                        RED + "Computer Wins: %d" + RESET + "   " +
                        YELLOW + "Ties: %d\n" + RESET,
                playerWins, computerWins, ties);

        System.out.printf("  " + BLUE + "Total Rounds Played: %d\n\n" + RESET, totalRounds);
    }

    // Show available choices
    private static void showChoices() {
        System.out.println(YELLOW + BOLD + "🎯 MAKE YOUR CHOICE:\n" + RESET);

        System.out.println("  " + GREEN + "1. " + EMOJIS[0] + " ROCK    (Press 1 or R)" + RESET);
        System.out.println("  " + BLUE + "2. " + EMOJIS[1] + " PAPER   (Press 2 or P)" + RESET);
        System.out.println("  " + PURPLE + "3. " + EMOJIS[2] + " SCISSORS (Press 3 or S)\n" + RESET);

        System.out.println("  " + RED + "Q. QUIT GAME" + RESET);
        System.out.println();
    }

    // Get player's choice with validation
    private static int getPlayerChoice(Scanner scanner) {
        while (true) {
            System.out.print(YELLOW + BOLD + "Enter your choice: " + RESET);
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "1":
                case "R":
                case "ROCK":
                    System.out.println(GREEN + "  You chose: ROCK ✊" + RESET);
                    return 0;
                case "2":
                case "P":
                case "PAPER":
                    System.out.println(BLUE + "  You chose: PAPER ✋" + RESET);
                    return 1;
                case "3":
                case "S":
                case "SCISSORS":
                    System.out.println(PURPLE + "  You chose: SCISSORS ✌️" + RESET);
                    return 2;
                case "Q":
                case "QUIT":
                    return -1;
                default:
                    System.out.println(RED + "  Invalid choice! Please enter R, P, S, or 1, 2, 3." + RESET);
                    System.out.println(YELLOW + "  (Type Q to quit the game)" + RESET);
            }
        }
    }

    // Display both player and computer choices
    private static void displayChoices(int playerChoice, int computerChoice) {
        System.out.println("\n" + CYAN + "════════════════════════════════════════════════════" + RESET);
        System.out.println(YELLOW + BOLD + "                    SHOWDOWN!" + RESET);
        System.out.println(CYAN + "════════════════════════════════════════════════════\n" + RESET);

        // Display with animation effect
        try {
            System.out.print("  " + GREEN + BOLD + "You: " + EMOJIS[playerChoice] + " " + CHOICES[playerChoice] + RESET);
            System.out.print("   vs   ");
            Thread.sleep(500);
            System.out.println(RED + BOLD + "Computer: " + EMOJIS[computerChoice] + " " + CHOICES[computerChoice] + RESET);
        } catch (InterruptedException e) {
            // Continue if interrupted
        }
    }

    // Determine the winner and update scores
    private static void determineWinner(int playerChoice, int computerChoice) {
        totalRounds++;

        if (playerChoice == computerChoice) {
            // Tie
            System.out.println("\n" + YELLOW + "════════════════════════════════════════════════════" + RESET);
            System.out.println(YELLOW + BOLD + "                    IT'S A TIE! 🤝" + RESET);
            System.out.println(YELLOW + "════════════════════════════════════════════════════" + RESET);
            ties++;
        } else if ((playerChoice == 0 && computerChoice == 2) || // Rock beats Scissors
                (playerChoice == 1 && computerChoice == 0) || // Paper beats Rock
                (playerChoice == 2 && computerChoice == 1)) { // Scissors beats Paper

            // Player wins
            System.out.println("\n" + GREEN + "════════════════════════════════════════════════════" + RESET);
            System.out.println(GREEN + BOLD + "                🎉 YOU WIN THIS ROUND! 🎉" + RESET);
            System.out.println(GREEN + "  " + WIN_PHRASES[playerChoice] + RESET);
            System.out.println(GREEN + "════════════════════════════════════════════════════" + RESET);
            playerWins++;
        } else {
            // Computer wins
            System.out.println("\n" + RED + "════════════════════════════════════════════════════" + RESET);
            System.out.println(RED + BOLD + "              💻 COMPUTER WINS THIS ROUND! 💻" + RESET);
            System.out.println(RED + "  " + WIN_PHRASES[computerChoice] + RESET);
            System.out.println(RED + "════════════════════════════════════════════════════" + RESET);
            computerWins++;
        }

        // Show updated score
        System.out.println("\n" + CYAN + "Updated Score: " + GREEN + "You " + playerWins + RESET +
                " - " + RED + computerWins + " Computer" + RESET +
                " (" + YELLOW + ties + " ties" + RESET + ")");
    }

    // Show final results when game ends
    private static void showFinalResults() {
        clearScreen();
        System.out.println(CYAN + "╔════════════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║                 " + YELLOW + BOLD + "FINAL RESULTS" + RESET + CYAN + "                 ║" + RESET);
        System.out.println(CYAN + "╚════════════════════════════════════════════════════╝\n" + RESET);

        System.out.println("  " + BLUE + "Total Rounds Played: " + totalRounds + RESET);
        System.out.println("  " + GREEN + "Your Wins: " + playerWins + RESET);
        System.out.println("  " + RED + "Computer Wins: " + computerWins + RESET);
        System.out.println("  " + YELLOW + "Ties: " + ties + RESET);

        System.out.println("\n" + PURPLE + "════════════════════════════════════════════════════" + RESET);

        // Determine overall winner
        if (playerWins > computerWins) {
            System.out.println(GREEN + BOLD + "            🏆 YOU ARE THE OVERALL WINNER! 🏆" + RESET);
            System.out.println(GREEN + "       You beat the computer by " + (playerWins - computerWins) + " points!" + RESET);
        } else if (computerWins > playerWins) {
            System.out.println(RED + BOLD + "          💻 COMPUTER IS THE OVERALL WINNER! 💻" + RESET);
            System.out.println(RED + "       Computer beat you by " + (computerWins - playerWins) + " points!" + RESET);
        } else {
            System.out.println(YELLOW + BOLD + "               🤝 IT'S A COMPLETE TIE! 🤝" + RESET);
            System.out.println(YELLOW + "           Both you and computer have " + playerWins + " wins!" + RESET);
        }

        // Win percentage
        if (totalRounds > 0) {
            double winPercentage = (playerWins * 100.0) / totalRounds;
            System.out.println("\n" + CYAN + "Your Win Percentage: " + String.format("%.1f", winPercentage) + "%" + RESET);
        }

        System.out.println(PURPLE + "════════════════════════════════════════════════════" + RESET);
    }

    // Bonus: Statistics tracking
    private static void showStatistics() {
        if (totalRounds == 0) return;

        System.out.println("\n" + CYAN + "📊 GAME STATISTICS:" + RESET);
        System.out.printf("  Win Rate: %.1f%%\n", (playerWins * 100.0) / totalRounds);
        System.out.printf("  Tie Rate: %.1f%%\n", (ties * 100.0) / totalRounds);

        // Show most common choice (if we were tracking it)
        System.out.println("\n" + YELLOW + "💡 TIPS FOR NEXT TIME:" + RESET);
        System.out.println("  • Rock is often chosen first");
        System.out.println("  • Try to predict computer's pattern");
        System.out.println("  • Don't be predictable yourself!");
    }
}