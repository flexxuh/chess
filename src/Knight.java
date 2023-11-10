// Written by Felix Agene,agene001

public class Knight {
    int row;
    int col;
    boolean isBlack;
    //constructor for knight
    public Knight(int initRow, int initCol, boolean initIsBlack){
        row = initRow;
        col = initCol;
        isBlack = initIsBlack;
    }

    public boolean isMoveLegal(Board b,int endRow, int endCol){
        //checks to see if valid move then checks to see if it is a knight move
        if(b.verifySourceAndDestination(row,col,endRow, endCol, isBlack)) {
            return b.verifyKnight(row,col,endRow, endCol);
        }
        return false;
    }
}
