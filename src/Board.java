
import java.lang.Math;
// Written by Felix Agene,agene001
public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];
    }
    // Accessor Methods
    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }
    public Piece[][] getBoard(){
        return board;
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        piece.setPosition(row, col);
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. Your Game class should not
    // directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        //checks to make sure selected piece is not null, then creates that piece and checks if move and destination is first valid
        //then checks to see if that pieces move is legal and if so it makes the endRow and endCol = piece
        if (getPiece(startRow, startCol) != null) {
            Piece piece = getPiece(startRow, startCol);
            if (verifySourceAndDestination(startRow, startCol, endRow, endCol, piece.getIsBlack())) {
                if (piece.isMoveLegal(this, endRow, endCol)) {
                    this.board[endRow][endCol] = piece;
                    this.board[startRow][startCol] = null;
                    piece.setPosition(endRow, endCol);
                    return true;
                }
            }
        }
        return false;
    }
    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        //loops through every piece to find king and if both are found it returns false but if only one is found, it prints that color as winner
        int countBlack = 0;
        int countWhite = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].getCharacter() == '\u2654') {
                        ++countWhite;

                    }
                    else if(board[i][j].getCharacter() == '\u265A') {
                        ++countBlack;

                    }
                }
            }
        }
        if(countBlack == 1 && countWhite == 1)
            return false;
        else if(countBlack == 1) {
            System.out.println("Black wins");
            this.clear();
            return true;
        }else {
            System.out.println("White wins");
            this.clear();
            return true;
        }
    }


    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        String[] s = new String[8];
        int k = 0;
        while(k<s.length) {
            s[k] = "";
            for (int i = 0; i <= 7; i++) {
                if(this.getPiece(k,i) != null)
                    s[k] += ("\u2001\u2001" + this.getPiece(k,i) + "\u2001\u2001|");
                else
                    s[k] += "\u2001\u2001\u2001\u2001\u2001| ";
            }
            s[k] += "\n";
            k++;
        }
        return " \u2001\u2001\u2001\u2001\u2001" + "\uFF10\u2001\u2001\u2001\u2001\u2001\uFF11\u2001\u2001\u2001\u2001\u2001\uFF12\u2001\u2001\u2001\u2001\u2001\uFF13\u2001\u2001\u2001\u2001\u2001\uFF14\u2001\u2001\u2001\u2001\u2001\uFF15\u2001\u2001\u2001\u2001 \uFF16\u2001\u2001\u2001\u2001\u2001\uFF17 \n" +
                "\uFF10\u2001|" + s[0] +"\n\uFF11\u2001|" + s[1] + "\n\uFF12\u2001|" + s[2] + "\n\uFF13\u2001|"+ s[3] +"\n\uFF14\u2001|" + s[4] + "\n\uFF15\u2001|" + s[5] +
                "\n\uFF16\u2001|" + s[6] + "\n\uFF17\u2001|" + s[7];
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        //iterates through all board positions and sets them to null
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++)
                board[i][j] = null;
        }

    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifyKnight(int startRow, int startCol, int endRow, int endCol) {
        //checks to make sure endRow is within 2 of start row or within 1
        // then checks each individual case of that and sees if col is within 1 or 2 respectfully
        if (endRow == startRow - 2 || endRow == startRow + 2) {
            if (endCol == startCol + 1 || endCol == startCol - 1)
                return true;
        } else if (endRow == startRow - 1 || endRow == startRow + 1) {
            if (endCol == startCol + 2 || endCol == startCol - 2)
                return true;
        }
        return false;
    }
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        // makes sure all values are between 0 and 8. then checks if start and end destination are the same and returns false if they are
        //if any value is less than 0 it returns false
        //else it get the piece at start and checks to make sure it isnt null
        //then checks to make sure piece isBlack == isBlack
        //then checks to make sure end is eithier null or pieces color does not == isBlack
        if(startRow < 8 && startCol < 8 && endRow<8 && endCol<8) {
            if (startRow == endRow && startCol == endCol)
                return false;
            else {
                if (startRow < 0 || startCol < 0 || endRow < 0 || endCol < 0)
                    return false;
                Piece piece = getPiece(startRow, startCol);
                if (piece != null) {
                    if (piece.getIsBlack() == isBlack) {
                        if (getPiece(endRow, endCol) == null || getPiece(endRow, endCol).getIsBlack() != isBlack)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        //makes sure endRow is between 1 of start row
        //then checks to see if endCol is within 1 of startCol
        if (endRow == startRow + 1 || endRow == startRow - 1 || endRow == startRow) {
            if (endCol == startCol - 1 || endCol == startCol + 1 || endCol == startCol) {
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        //checks to make sure startRow is the same as endRow
        //then checks to see if startCol is to the right of endCol
        //then iterates though every piece in between and makes sure == null
        if (startRow == endRow) {
            if (startCol - endCol > 0) {
                for (int i = startCol - 1; i > endCol; i--) {
                    if (board[startRow][i] != null)
                        return false;
                }
                return true;
            } else{
                for (int i = startCol + 1; i < endCol; i++) {
                    if (board[startRow][i] != null)
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        //checks to make sure startCol is the same as endCol
        //then checks to see if startRow is to the below of endCol
        //then iterates though every piece in between and makes sure == null
        if (startCol == endCol) {
            if (startRow - endRow >= 0) {
                for (int i = endRow + 1; i < startRow; i++) {
                    if (board[i][startCol] != null)
                        return false;
                }
                return true;
            } else if ((startRow - endRow) < 0) {
                for (int i = startRow + 1; i < endRow; i++) {
                    if (board[i][startCol] != null)
                        return false;
                }
            }
            return true;
        }
        return false;
    }
    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        //checks to make sure difference in rows is the same difference in col(diagonal)
        //then first checks if startRow is below endRow
        //then checks if startCol is to the right of endCol
        //then iterates through each piece in diagonal spot and makes sure is null
        int diffRow = Math.abs(startRow - endRow);
        int diffCol = Math.abs(startCol - endCol);
        if (diffRow == diffCol) {
            if (startRow - endRow > 0) {
                if (startCol - endCol > 0) {
                    for (int i = 1; i <= diffRow - 1; i++) {
                        if (board[startRow - i][startCol - i] != null)
                            return false;
                    }
                    return true;
                } else {
                    for (int i = 1; i <= diffRow - 1; i++){
                        if (board[startRow - i][startCol + i] != null)
                            return false;
                    }
                    return true;
                }
            }else {
                if (startCol - endCol > 0) {
                    for (int i = 1; i <= diffRow - 1; i++) {
                        if (board[startRow + i][startCol - i] != null)
                            return false;
                    }
                    return true;
                } else {
                    for (int i = 1; i <= diffRow - 1; i++){
                        if (board[startRow + i][startCol + i] != null)
                            return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
