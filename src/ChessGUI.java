import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import edu.princeton.cs.algs4.Picture;

public class ChessGUI extends MouseAdapter {
    Picture pawn_wht = new Picture("pawn_wht.png");
    
    Picture rook_wht = new Picture("rook_wht.png");
    Picture bishop_wht = new Picture("bishop_wht.png");
    Picture knight_wht = new Picture("knight_wht.png");
    Picture queen_wht = new Picture("queen_wht.png");
    Picture king_wht = new Picture("king_wht.png");
    Picture pawn_blk = new Picture("pawn_blk.png");
    Picture rook_blk = new Picture("rook_blk.png");
    Picture bishop_blk = new Picture("bishop_blk.png");
    Picture knight_blk = new Picture("knight_blk.png");
    Picture queen_blk = new Picture("queen_blk.png");
    Picture king_blk = new Picture("king_blk.png");

    boolean white = true;
    
    private final int count = 0;
    Color tan = new Color(196, 158, 120);
    Color br = new Color(171, 126, 76);
    
    JLabel label = new JLabel("Number of clicks: " + count);
    JLabel[] labs = new JLabel[64];
    
    boolean state = false;
    int[] arr = new int[2];
    Board board = new Board();

    JFrame frame = new JFrame();
    
    public ChessGUI() {
        //set up board
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.addMouseListener(this);
        JLabel pan2 = new JLabel("Chess");
        panel.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            int y = i % 2;
            for (int j = 0; j < 8; j++) {
                int x = j % 2;
                
                JLabel la = new JLabel();
                la.setOpaque(true);
                if (y == 0) {
                    if (x == 0) {
                        la.setBackground(tan);
                        labs[XYto1D(j, i)] = la;
                    } else {
                        la.setBackground(br);
                        labs[XYto1D(j, i)] = la;
                    }
                } else {
                    if (x == 0) {
                        la.setBackground(br);
                        labs[XYto1D(j, i)] = la;
                    } else {
                        la.setBackground(tan);
                        labs[XYto1D(j, i)] = la;
                    }
                }
                if (i == 1) {
                    la.setIcon(pawn_blk.getJLabel().getIcon());
                    labs[XYto1D(j, i)] = la;
                    la.setVisible(true);
                }
                if (i == 6) {
                    la.setIcon(pawn_wht.getJLabel().getIcon());
                    labs[XYto1D(j, i)] = la;
                    la.setVisible(true);
                }
                if (i == 0) {
                    if (j == 0 || j == 7) {
                        la.setIcon(rook_blk.getJLabel().getIcon());
                        labs[XYto1D(j, i)] = la;
                        la.setVisible(true);
                    }
                    if (j == 1 || j == 6) {
                        la.setIcon(knight_blk.getJLabel().getIcon());
                        labs[XYto1D(j, i)] = la;
                        la.setVisible(true);
                    }
                    if (j == 2 || j == 5) {
                        la.setIcon(bishop_blk.getJLabel().getIcon());
                        labs[XYto1D(j, i)] = la;
                        la.setVisible(true);
                    }
                    if (j == 4) {
                        la.setIcon(king_blk.getJLabel().getIcon());
                        labs[XYto1D(j, i)] = la;
                        la.setVisible(true);
                    }
                    if (j == 3) {
                        la.setIcon(queen_blk.getJLabel().getIcon());
                        labs[XYto1D(j, i)] = la;
                        la.setVisible(true);
                    }
                }
                if (i == 7) {
                    if (j == 0 || j == 7) {
                        la.setIcon(rook_wht.getJLabel().getIcon());
                        labs[XYto1D(j, i)] = la;
                        la.setVisible(true);
                    }
                    if (j == 1 || j == 6) {
                        la.setIcon(knight_wht.getJLabel().getIcon());
                        labs[XYto1D(j, i)] = la;
                        la.setVisible(true);
                    }
                    if (j == 2 || j == 5) {
                        la.setIcon(bishop_wht.getJLabel().getIcon());
                        labs[XYto1D(j, i)] = la;
                        la.setVisible(true);
                    }
                    if (j == 4) {
                        la.setIcon(king_wht.getJLabel().getIcon());
                        labs[XYto1D(j, i)] = la;
                        la.setVisible(true);
                    }
                    if (j == 3) {
                        la.setIcon(queen_wht.getJLabel().getIcon());
                        labs[XYto1D(j, i)] = la;
                        la.setVisible(true);
                    }
                }
                panel.add(la);
                frame.repaint();
            }
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our ChessGUI");
        frame.pack();
        frame.add(pan2, BorderLayout.NORTH);
         int y= pan2.getHeight();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setVisible(true);
        int cols = 800/80;
        int rows = 600/80;
        
    }
    public void game(int starX,int starY, int endX,int endY){
        System.out.println("board:\n" + board);
        int startRow = starY;
        int startCol = starX;
        int endRow = endY;
        int endCol = endX;
        if (white) {
            //checks first to see if starting piece color is there and white, then checks to see if desired move is possible
            if (board.getPiece(startRow, startCol) != null) {
                if (!board.getPiece(startRow, startCol).getIsBlack()) {
                    if (board.movePiece(startRow, startCol, endRow, endCol))
                        white = false;
                    else
                        System.out.println("Not a valid move");
                } else
                    System.out.println("Not your piece");
            } else
                System.out.println("no Piece there");
        }else{
            //checks first to see if starting piece color is there and black, then checks to see if desired move is possible
            if (board.getPiece(startRow, startCol) != null) {
                if (board.getPiece(startRow, startCol).getIsBlack()) {
                    if (board.movePiece(startRow, startCol, endRow, endCol))
                        white = true;
                    else
                        System.out.println("Not a valid move");
                } else
                    System.out.println("Not your piece");
            } else
                System.out.println("no Piece there");

        }
    }
    
    
    public int XYto1D(int x, int y) {
        return (x * 8) + y;
    }
    public void mouseClicked(MouseEvent me){
        int x = me.getX()/98;
        int y = (me.getY()-2)/68;
        if(!state){
            state = true;
            arr[0] = x;
            arr[1] = y;
    }
        else{
            game(arr[0],arr[1],x,y);
            if(board.isGameOver()){
                frame.dispose();
            }
            reDraw();
            state = false;
            if(board.getPiece(arr[1],arr[0])==null) {
                labs[XYto1D(arr[0], arr[1])].setIcon(null);
            }
        }
    }
    public void reDraw(){
        Piece[][] bo2 = board.getBoard();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) {
                if (bo2[i][j] != null) {
                    char cha = bo2[i][j].getCharacter();
                    switch (cha) {
                        case '♙':
                            labs[XYto1D(j, i)].setIcon(pawn_wht.getJLabel().getIcon());
                            break;
                        case '♟':
                            labs[XYto1D(j, i)].setIcon(pawn_blk.getJLabel().getIcon());
                            break;
                        case '♖':
                            labs[XYto1D(j, i)].setIcon(rook_wht.getJLabel().getIcon());
                            break;
                        case '♜':
                            labs[XYto1D(j, i)].setIcon(rook_blk.getJLabel().getIcon());
                            break;
                        case '♞':
                            labs[XYto1D(j, i)].setIcon(knight_blk.getJLabel().getIcon());
                            break;
                        case '♘':
                            labs[XYto1D(j, i)].setIcon(knight_wht.getJLabel().getIcon());
                            break;
                            // Bishop chars
                        case '♝':
                            labs[XYto1D(j, i)].setIcon(bishop_blk.getJLabel().getIcon());
                            break;
                        case '♗':
                            labs[XYto1D(j, i)].setIcon(bishop_wht.getJLabel().getIcon());
                            break;
                            // Queen chars
                        case '♛':
                            labs[XYto1D(j, i)].setIcon(queen_blk.getJLabel().getIcon());
                            break;
                        case '♕':
                            labs[XYto1D(j, i)].setIcon(queen_wht.getJLabel().getIcon());
                            break;
                            // King chars
                        case '♚':
                            labs[XYto1D(j, i)].setIcon(king_blk.getJLabel().getIcon());
                            break;
                        case '♔':
                            labs[XYto1D(j, i)].setIcon(king_wht.getJLabel().getIcon());
                            break;
                        default:
                    }
                }
            }
        }
        frame.repaint();
    }
    
    public static void main(String[] args) {
        new ChessGUI();
        //creates board object and scanner object

    }
    
    
}
