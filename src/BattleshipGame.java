import java.util.Scanner;

public class BattleshipGame {
    public static void main(String[] args) {
        do {
            playGame();
        } while (askToPlay());
    }
    private static void playGame() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        System.out.println("Game Start!");
        while (!ocean.isGameOver()) {
            ocean.print();
            acceptShot(ocean);
        }
        System.out.println("Game Over! You have sunk all the ships!");
        printFinalScores(ocean);
    }

    /**
	 * Accepts a shot from the user and updates the ocean state.
	 */
    private static void acceptShot(Ocean ocean) {
        Scanner scanner = new Scanner(System.in);
        int row = -1, column = -1;
        while(true) {
            try {
                System.out.print("Enter the row of your shot: ");
                row = scanner.nextInt();
                if (row < 0 || row >= 10) {
                    System.out.println("Invalid row!");
                } else break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter integer.");
                scanner.nextLine();
            }
        }

        while (true) {
            try {
                System.out.print("Enter the column of your shot: ");
                column = scanner.nextInt();
                if (column < 0 || column >= 10) {
                    System.out.println("Invalid column!");
                } else break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter integer.");
                scanner.nextLine();
            }
        }

        boolean hit = ocean.shootAt(row, column);
        if (hit) {
            if (ocean.getShipArray()[row][column].isSunk()){
                System.out.println("You just sunk a " + ocean.getShipArray()[row][column].getShipType() + ".");
            } else {
                System.out.println("Hit!");
            }
        } else {
            System.out.println("Miss!");
        }
    }

    private static void printFinalScores(Ocean ocean) {
        System.out.println("Final Scores:");
        System.out.println("Total shots fired: " + ocean.getShotsFired());
        ocean.print();
    }

    /**
	 * @return true if the user wants to play again, false otherwise
	 */
    private static boolean askToPlay() {
        Scanner scanner = new Scanner(System.in);
        String answer;
        while(true) {
            System.out.println("Do you want to play again? (y/n): ");
            answer = scanner.next();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
            }
        }
    }

}
