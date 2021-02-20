import Piece.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {
    public static final int TILE_X_COUNT = 9;
    public static final int TILE_Y_COUNT = 11;
    private Object[][] pieceCollection;
    private Object selectedPiece;
    private Random RANDOM = new Random();
    private int TURN_COUNTER = 1;

    public GameBoard() {
        this.pieceCollection = new Object[TILE_X_COUNT][TILE_Y_COUNT];
        RANDOM = new Random();

        createObstacles();
        pieceCreator();

        this.setSize(550, 450);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);
    }
    // Painting the game board
    public void paint(Graphics g) {
        super.paint(g);

        for(int row = 1; row < 8; ++row) {
            for(int col = 1; col < 10; ++col) {
                GameTiles tile = new GameTiles(row, col);
                tile.render(g);

                if(this.hasBoardPiece(row, col)) {
                    Figure figure = (Figure) this.getBoardPiece(row, col);
                    figure.render(g);
                }

            }
        }
    }

    public void createObstacles () {

        for (int obstacle = 0; obstacle < 5; obstacle++) {
            int X = RANDOM.nextInt(7);
            int Y = RANDOM.nextInt(9);
            if(X == 0) {X++;}
            if(Y == 0) {Y++;}
            this.pieceCollection[X][Y] = new Obstacle(X, Y, 4);
            System.out.println(X + "" + Y);
        }
    }

    private int playerTurn() {
        if(TURN_COUNTER % 2 == 0) {
            TURN_COUNTER++;
            System.out.println("PLayer A");
            return 1;
        } else {
            System.out.println("Player B");
            return 2;
        }
    }

    private void pieceCreator () {
        this.pieceCollection[1][1] = new Elf(1,1,11);
        this.pieceCollection[1][3] = new Dwarf(1,3, 12);
        this.pieceCollection[1][5] = new Knight(1,5,13);
        this.pieceCollection[7][5] = new Elf(7,5,21);
        this.pieceCollection[7][7] = new Dwarf(7,7, 22);
        this.pieceCollection[7][9] = new Knight(7,9,23);
    }

//    private int wrongPiece () {
        //if this.selectedPiece id == 1 , 2, 3
//    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private Object getBoardPiece(int row, int col) {
        return this.pieceCollection[row][col];
    }
    private boolean hasBoardPiece(int row, int col) {
        return this.getBoardPiece(row, col) != null;
    }
    private int getBoardDimensionBasedOnCoordinates(int coordinates) {
        return coordinates / 100;
    }
}
