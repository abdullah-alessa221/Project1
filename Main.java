import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        int turnChoice = 0;
        int symbolChoice = 0;
        String firstPlayerName = "";
        String secondPlayerName = "Computer";
        String symbol;
        char firstPlayerSymbol = 'X';
        char secondPlayerSymbol = 'O';
        boolean vsComputer = false;

        System.out.println("\n                      Tic Tac Toe Game!\n");

        try {
            // -------------------- Turn Choice --------------------
            while (true) {
                System.out.println("Press 1 if you want to play with your friend or press 2 to play with computer.");
                try {
                    turnChoice = input.nextInt();
                    if (turnChoice == 1) {
                        System.out.print("Enter First player name: ");
                        firstPlayerName = input.next();
                        System.out.print("Enter second player name: ");
                        secondPlayerName = input.next();
                        break;
                    } else if (turnChoice == 2) {
                        vsComputer = true;
                        System.out.print("Enter your name: ");
                        firstPlayerName = input.next();
                        System.out.println("You will play against the computer.");
                        break;
                    } else {
                        System.out.println("Invalid choice! Please enter 1 or 2.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    input.next();
                }
            }

            System.out.println("\nWelcome " + firstPlayerName + " and " + secondPlayerName);

            // -------------------- Symbol Choice --------------------
            while (true) {
                System.out.println("\nPress 1 if you want to choose the symbol manually or press 2 for randomly choosing.");
                try {
                    symbolChoice = input.nextInt();
                    if (symbolChoice == 1) {
                        while (true) {
                            System.out.print(firstPlayerName + ", choose your symbol (X or O): ");
                            symbol = input.next().toUpperCase();
                            if (symbol.equals("X")) {
                                firstPlayerSymbol = 'X';
                                secondPlayerSymbol = 'O';
                                break;
                            } else if (symbol.equals("O")) {
                                firstPlayerSymbol = 'O';
                                secondPlayerSymbol = 'X';
                                break;
                            } else {
                                System.out.println("Invalid input! Please enter X or O.");
                            }
                        }
                        break;
                    } else if (symbolChoice == 2) {
                        if (random.nextBoolean()) {
                            firstPlayerSymbol = 'X';
                            secondPlayerSymbol = 'O';
                        } else {
                            firstPlayerSymbol = 'O';
                            secondPlayerSymbol = 'X';
                        }
                        System.out.println("Symbols assigned randomly.");
                        break;
                    } else {
                        System.out.println("Invalid choice! Please enter 1 or 2.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    input.next();
                }
            }

            System.out.println(firstPlayerName + " symbol is: " + firstPlayerSymbol);
            System.out.println(secondPlayerName + " symbol is: " + secondPlayerSymbol);

            // -------------------- Random Turn --------------------
            boolean isFirstPlayerTurn = random.nextBoolean();
            System.out.println("\nRandomly selected player to start: " + (isFirstPlayerTurn ? firstPlayerName : secondPlayerName));

            // -------------------- Initialize Board --------------------
            String[][] board = new String[3][3];
            initializeBoard(board);

            int totalMoves = 0;
            while (true) {
                displayBoard(board);

                try {
                    if (isFirstPlayerTurn) {
                        playerMove(input, board, firstPlayerName, String.valueOf(firstPlayerSymbol));
                        totalMoves++;
                        if (checkWinner(board, String.valueOf(firstPlayerSymbol))) {
                            displayBoard(board);
                            System.out.println(firstPlayerName + " wins!");
                            break;
                        }
                    } else {
                        if (vsComputer) {
                            computerMove(board, String.valueOf(secondPlayerSymbol));
                        } else {
                            playerMove(input, board, secondPlayerName, String.valueOf(secondPlayerSymbol));
                        }
                        totalMoves++;
                        if (checkWinner(board, String.valueOf(secondPlayerSymbol))) {
                            displayBoard(board);
                            System.out.println(secondPlayerName + " wins!");
                            break;
                        }
                    }

                    if (totalMoves == 9) {
                        displayBoard(board);
                        System.out.println("It's a draw!");
                        break;
                    }

                    isFirstPlayerTurn = !isFirstPlayerTurn;

                } catch (Exception e) {
                    System.out.println("An error occurred during the game. Please try again.");
                }
            }

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } finally {
            input.close();
        }
    }

    public static void displayBoard(String[][] board) {
        System.out.println("\nCurrent Board:\n");
        for (int i = 0; i < 3; i++) {
            System.out.printf("  %s  |  %s  |  %s  \n", board[i][0], board[i][1], board[i][2]);
            if (i < 2) System.out.println("-------------------");
        }
    }

    public static void initializeBoard(String[][] board) {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = String.valueOf(count++);
            }
        }
    }

    public static void playerMove(Scanner input, String[][] board, String playerName, String symbol) {
        int choice;

        while (true) {
            try {
                System.out.print(playerName + " turn. Enter a cell number (1-9): ");
                choice = input.nextInt();

                if (choice < 1 || choice > 9) {
                    System.out.println("Invalid input! Please enter a number between 1 and 9.");
                    continue;
                }

                int row = (choice - 1) / 3;
                int col = (choice - 1) % 3;

                if (board[row][col].equals("X") || board[row][col].equals("O")) {
                    System.out.println("Cell already taken! Choose another one.");
                } else {
                    board[row][col] = symbol;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                input.next();
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    public static void computerMove(String[][] board, String symbol) {
        Random rand = new Random();
        System.out.println("Computer is thinking...");

        while (true) {
            int choice = rand.nextInt(9);
            int row = choice / 3;
            int col = choice % 3;

            if (!board[row][col].equals("X") && !board[row][col].equals("O")) {
                board[row][col] = symbol;
                System.out.println("Computer chose cell " + ((row * 3 + col) + 1));
                break;
            }
        }
    }

    public static boolean checkWinner(String[][] board, String symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(symbol) && board[i][1].equals(symbol) && board[i][2].equals(symbol))
                return true;
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j].equals(symbol) && board[1][j].equals(symbol) && board[2][j].equals(symbol))
                return true;
        }
        if (board[0][0].equals(symbol) && board[1][1].equals(symbol) && board[2][2].equals(symbol))
            return true;

        if (board[0][2].equals(symbol) && board[1][1].equals(symbol) && board[2][0].equals(symbol))
            return true;

        return false;
    }
}
