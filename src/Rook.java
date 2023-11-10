// Written by Felix Agene,agene001

public class Rook {
    int row;
    int col;
    boolean isBlack;
    // constructor for rook
    public Rook(int initRow, int initCol, boolean initIsBlack){
        row = initRow;
        col = initCol;
        isBlack = initIsBlack;
    }

    public boolean isMoveLegal(Board b,int endRow, int endCol){
        //checks to make sure move is legal then checks if horizontal move or vertical move
        if(b.verifySourceAndDestination(row,col,endRow, endCol, isBlack)) {
            return (b.verifyHorizontal(row, col, endRow, endCol) || b.verifyVertical(row, col, endRow, endCol));
        }
        return false;
    }
}
