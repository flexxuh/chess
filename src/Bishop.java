// Written by Felix Agene,agene001

public class Bishop {

    int row;
    int col;
    boolean isBlack;
    // constructor for bishop
    public Bishop(int initRow, int initCol, boolean initIsBlack) {
        row = initRow;
        col = initCol;
        isBlack = initIsBlack;
    }
    //checks move to make sure legal and then diagonal
    public boolean isMoveLegal(Board b, int endRow, int endCol) {

        if (b.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            return b.verifyDiagonal(row, col, endRow, endCol);
        }
        return false;
    }
}

