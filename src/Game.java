
// Written by Felix Agene,agene001
import java.util.Scanner;
public class Game {

    public static void main(String[] args) {
        //creates board object and scanner object
        Board b = new Board();
        Scanner scan = new Scanner(System.in);
        boolean white = true;
        //loads beginning chess position
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", b);
        int[] inputNums;
        while (!b.isGameOver()) {
            System.out.println("board:\n" + b);
            //checks to see if its whites turn
            if (white)
                System.out.println("It is currently White's turn to play\nWhat is your move?(format:[start row] [start col] -> [end row] [end col] ");
            else
                System.out.println("It is currently Black's turn to play\nWhat is your move?(format:[start row] [start col] [end row] [end col] ");
            //asks for input and turns it into startRow, startCol, endRow,endCol
            String turn = scan.nextLine();
            char[] a = turn.toCharArray();
            inputNums = new int[4];
            int k = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] >= '0' && a[i] <= '9') {
                    inputNums[k] = (int) a[i] - 48;
                    k++;
                }
            }
            int startRow = inputNums[0];
            int startCol = inputNums[1];
            int endRow = inputNums[2];
            int endCol = inputNums[3];
            if (white) {
                //checks first to see if starting piece color is there and white, then checks to see if desired move is possible
                if (b.getPiece(startRow, startCol) != null) {
                    if (!b.getPiece(startRow, startCol).getIsBlack()) {
                        if (b.movePiece(startRow, startCol, endRow, endCol))
                            white = false;
                        else
                            System.out.println("Not a valid move");
                    } else
                        System.out.println("Not your piece");
                } else
                    System.out.println("no Piece there");
            }else{
                //checks first to see if starting piece color is there and black, then checks to see if desired move is possible
                if (b.getPiece(startRow, startCol) != null) {
                    if (b.getPiece(startRow, startCol).getIsBlack()) {
                        if (b.movePiece(startRow, startCol, endRow, endCol))
                            white = true;
                        else
                            System.out.println("Not a valid move");
                    } else
                        System.out.println("Not your piece");
                } else
                    System.out.println("no Piece there");

            }
        }
    }
}