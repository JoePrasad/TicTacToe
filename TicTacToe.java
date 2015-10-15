import java.util.Scanner;

public class TicTacToe
{
  public static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args)
  {

    final int SIZE = 3;
    char[][] board = new char[SIZE][SIZE]; // game board

    resetBoard(board); // initialize the board 

    
    System.out.println("===== WELCOME TO THE TIC-TAC-TOE GAME!! =====\n");
    printBoard(board); // first text that appears that welcomes the user

    // Then ask the user which symbol (x or o) he/she wants to play.
    System.out.print("  Which symbol do you want to play, \"x\" or \"o\"? ");
    char user1 = scanner.next().toLowerCase().charAt(0);
    char user2 = (user1 == 'x') ? 'o' : 'x'; // user 2 will play as the other option

    // Also ask whether or not user1 wants to go first.
    System.out.println();
    System.out.print("  Which user wants to go first? (1/2) ");
    char input = scanner.next().toLowerCase().charAt(0);

    int turn;  // 0 -- the user, 1 -- user 2
    int remainCount = SIZE * SIZE; // empty cell count

    // THE VERY FIRST MOVE.
    if (input == '1') {
      turn = 0;
      user(board, user1); // user1 puts his/her first tic
    }
    else {
      turn = 1;
      user2Play(board, user2); // user2 puts its first tic
    }
    // Show the board, and decrement the count of remaining cells.
    printBoard(board);
    remainCount--;

    // Play the game until either one wins.
    boolean done = false;
    int winner = -1;   // 0 -- the user, 1 -- the computer, -1 -- draw

    while (!done && remainCount > 0) {
      // If there is a winner at this time, set the winner and the done flag to true.
      done = isWinningBoard(board, turn, user1, user2); // Did the turn won?

      if (done)
        winner = turn; // the one who made the last move won the game
      else {
        // No winner yet.  Find the next turn and play.
        turn = (turn + 1 ) % 2;

        if (turn == 0)
          user(board, user1);
        else
          user2Play(board, user2);

        // Show the board after one tic, and decrement the rem count.
        printBoard(board);
        remainCount--;
      }
    }

    // Winner is found.  Declare the winner.
    if (winner == 0)
      System.out.println("\n** user1 WON.  **");
    else if (winner == 1)
      System.out.println("\n** user2 won  **");
    else
      System.out.println("\n** DRAW... **");

  }

  public static void resetBoard(char[][] brd)
  {
    for (int i = 0; i < brd.length; i++)
      for (int j = 0; j < brd[0].length; j++)
        brd[i][j] = ' ';
  }

  public static void printBoard(char[][] board)
  {
    int numRow = board.length;
    int numCol = board[0].length;

    System.out.println();

    // First write the column header
    System.out.print("    ");
    for (int i = 0; i < numCol; i++)
      System.out.print(i + "   ");
    System.out.print('\n');

    System.out.println(); // blank line after the header

    // The write the table
    for (int i = 0; i < numRow; i++) {
      System.out.print(i + "  ");
      for (int j = 0; j < numCol; j++) {
        if (j != 0)
          System.out.print("|");
        System.out.print(" " + board[i][j] + " ");
      }

      System.out.println();

      if (i != (numRow - 1)) {
        // separator line
        System.out.print("   ");
        for (int j = 0; j < numCol; j++) {
          if (j != 0)
            System.out.print("+");
          System.out.print("---");
        }
        System.out.println();
      }
    }
    System.out.println();
  }

  public static void user(char[][] brd, char usym)
  {
    System.out.print("\nEnter the row and column indices: ");
    int rowIndex = scanner.nextInt();
    int colIndex = scanner.nextInt();

    while (brd[rowIndex][colIndex] != ' ') {
      System.out.print("\n!! The cell is already taken.\nEnter the row and column indices: ");
      rowIndex = scanner.nextInt();
      colIndex = scanner.nextInt();
    }

    brd[rowIndex][colIndex] = usym;
  }

  public static void user2Play(char[][] brd, char usm2)
  {
      
    // Find the first empty cell and put a tic there.
    System.out.print("\nEnter the row and column indices: ");
    int rowIndex = scanner.nextInt();
    int colIndex = scanner.nextInt();

    while (brd[rowIndex][colIndex] != ' ') {
      System.out.print("\n!! The cell is already taken.\nEnter the row and column indices: ");
      rowIndex = scanner.nextInt();
      colIndex = scanner.nextInt();
    }

    brd[rowIndex][colIndex] = usm2;
      
  }

  public static boolean isWinningBoard(char[][] board, int turn, char uM, char u2M)
  {
    char sym;
    if (turn == 0)
      sym = uM;
    else
      sym = u2M;

    int i, j;
    boolean win = false;

    // Check win by a row
    for (i = 0; i < board.length && !win; i++) {
      for (j = 0; j < board[0].length; j++) {
        if (board[i][j] != sym)
          break;
      }
      if (j == board[0].length)
        win = true;
    }

    // Check win by a column
    for (j = 0; j < board[0].length && !win; j++) {
      for (i = 0; i < board.length; i++) {
        if (board[i][j] != sym)
          break;
      }
      if (i == board.length)
        win = true;
    }

    // Check win by a diagonal (1)
    if (!win) {
      for (i = 0; i < board.length; i++) {
        if (board[i][i] != sym)
          break;
      }
      if (i == board.length)
        win = true;
    }

    // Check win by a diagonal (2)
    if (!win) {
      for (i = 0; i < board.length; i++) {
        if (board[i][board.length - 1 - i] != sym)
          break;
      }
      if (i == board.length)
        win = true;
    }

    // Finally return win
    return win;
  }
}
