// Written by Felix Agene,agene001

public class King {
    int row;
    int col;
    boolean isBlack;
    //constructor for king

    public King(int initRow, int initCol, boolean initIsBlack) {
        row = initRow;
        col = initCol;
        isBlack = initIsBlack;
    }

    public boolean isMoveLegal(Board b, int endRow, int endCol) {
        //checks to make sure move is legal then checks if adjacent move
        if (b.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            return b.verifyAdjacent(row, col, endRow, endCol);
        }
        return false;
    }
}
