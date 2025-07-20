import java.util.Scanner;
class Demo {
    static char[][] board;

    public Demo() {
        board = new char[3][3];
        initBoard();
    }

    static void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void dispBoard() {
        System.out.println("------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("------------");
        }
    }

    static void placeChar(int row, int col, char ch) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            board[row][col] = ch;
        } else {
            System.out.println("Invalid position....");
        }
    }

    static boolean checkColWin() {
        for (int i = 0; i <= 2; i++) {
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkRowWin() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagonalWin() {
        return (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
               (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]);
    }

    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

class NameOfPlayer {
    String name;
    char ch;
    Demo demo;

    NameOfPlayer(String name, char ch, Demo demo) {
        this.name = name;
        this.ch = ch;
        this.demo = demo;
    }

    void makeMove() {
        Scanner scan = new Scanner(System.in);
        int row = -1, col = -1;
        while (true) {
            System.out.println(name + ", enter your move (row and column between 0 and 2):");
            try {
                row = scan.nextInt();
                col = scan.nextInt();

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Invalid input. Row and column must be between 0 and 2.");
                } else if (!isValidMove(row, col)) {
                    System.out.println("Cell already taken. Try again.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter numbers only.");
                scan.nextLine();
            }
        }
        Demo.placeChar(row, col, ch);
    }

    boolean isValidMove(int row, int col) {
        return Demo.board[row][col] == ' ';
    }
}

public class TicTacGame {
    public static void main(String[] args) {
        Demo d = new Demo();
        NameOfPlayer p1 = new NameOfPlayer("Aravind", 'X', d);
        NameOfPlayer p2 = new NameOfPlayer("Ashok", 'O', d);

        NameOfPlayer cp = p1;
        while (true) {
            System.out.println(cp.name + "'s turn");
            cp.makeMove();
            Demo.dispBoard();

            if (Demo.checkColWin() || Demo.checkRowWin() || Demo.checkDiagonalWin()) {
                System.out.println(cp.name + " has won!");
                break;
            } else if (Demo.isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            cp = (cp == p1) ? p2 : p1;
        }
    }
}
