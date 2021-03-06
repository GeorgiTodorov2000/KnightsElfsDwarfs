import Piece.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {
    public static final int TILE_X_COUNT = 9;
    public static final int TILE_Y_COUNT = 11;
    private Object[][] pieceCollection;
    private Object selectedPiece;
    private Object attackPiece;
    private Random RANDOM = new Random();
    private int TURN_COUNTER = 1;
    private Object trap;
    private int F_ROW = 0;
    private int F_COL = 0;
    private int S_COL = 0;
    private int S_ROW = 0;
    public Figure figure;
    JLabel l1 = null;

    public GameBoard() {
        this.pieceCollection = new Object[TILE_X_COUNT][TILE_Y_COUNT];
        RANDOM = new Random();

        createObstacles();
        pieceCreator();
        sideMenu();


        this.setSize(750, 450);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);
    }

    public void sideMenu() {
        if(l1 != null) {
            this.remove(l1);
        }
        String currentTurn;
        if(playerTurn() == 1) {
            currentTurn = "Player 1";
        } else {
            currentTurn = "Player 2";
        }
        l1=new JLabel(currentTurn);
        l1.setBounds(544,20, 100,30);
        JButton attack=new JButton("ATTACK!");
        JButton move=new JButton("Move");
        JButton heal=new JButton("Heal");

        attack.setBounds(520,50,95,30);
        move.setBounds(520,100,95,30);
        heal.setBounds(520,150,95,30);

        this.add(l1);this.add(attack);this.add(move);this.add(heal);
        this.setLayout(null);
        this.setVisible(true);
    }

//    private int buttonAttack(int row, int col, Figure figure) {
//        JButton attack=new JButton("ATTACK!");
//
//        attack.setBounds(520,50,95,30);
//        attack.addActionListener(e ->
//        {
//            attack(row,col);
//        });
//        return 1;
//    }
//
//    public void buttonHeal(int fRow, int fCol, Figure figure) {
//
//        JButton heal=new JButton("Heal");
//        heal.setBounds(520,150,95,30);
//
//        heal.addActionListener(e -> {
//            heal(fRow, fCol, figure);
//        });
//
//    }
//
//    public void buttonMove(int fRow, int fCol, Figure figure) {
//        JButton move=new JButton("Move");
//
//
//
//        move.setBounds(520,100,95,30);
//        move.addActionListener(e -> {
//            moveFigure(fRow, fCol, figure);
//        });
//    }






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
        int maxObstacles = RANDOM.nextInt(5);
        ++maxObstacles;
        for (int obstacle = 0; obstacle < maxObstacles; obstacle++) {
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
            return 1;
        } else {
            return 2;
        }
    }

    private void pieceCreator () {
        this.pieceCollection[1][1] = new Elf(1,1,11, 5, 10, 1, 3);
        this.pieceCollection[1][3] = new Dwarf(1,3, 12,6, 12, 2, 2);
        this.pieceCollection[1][5] = new Knight(1,5,13, 8 , 15, 3, 1);
        this.pieceCollection[7][5] = new Elf(7,5,21, 5, 10, 1, 3);
        this.pieceCollection[7][7] = new Dwarf(7,7, 22,6, 12, 2, 2);
        this.pieceCollection[7][9] = new Knight(7,9,23, 8 , 15, 3, 1);
    }

    private boolean isTileEmpty(int row, int col) {
        if(this.pieceCollection[row][col] != null) {

            return false;
        }
        return true;
    }

    public void attack(int row, int col) {
        System.out.println("ATTACK!!!");

        figure.setHealth(secondFigure(row, col));
        System.out.println(secondFigure(row, col));


    }

    public int firstFigure () {
        this.pieceCollection[figure.getRow()][figure.getCol()] = this.selectedPiece;
        figure = (Figure) this.selectedPiece;
        int attack = figure.getAttack();
        return attack;
    }
    public int secondFigure (int row, int col) {
        this.pieceCollection[row][col] = this.attackPiece;
        figure = (Figure) this.attackPiece;
        int defence = figure.getArmor();
        int health = figure.getHealth();
        int healthAfterAttack = health - (firstFigure() - defence);
        return healthAfterAttack;
    }
//
//    public void heal(int row, int col, Figure figure) {
//
//        int healing = RANDOM.nextInt(6);
//        ++healing;
//        //TODO Figures shouldn't be able to heal above max health
//        figure.setHealth(figure.getHealth()+healing);
//        if (healing % 2 == 1) {
//            //TODO Doesn't change player turn
//        }
//        System.out.println(healing);
//    }
//
//    private void moveFigure(int row, int col, Figure figure) {
//        if(isThatFigureYours() == 1) {
//            if(isTileEmpty(row, col)) {
//                if (figure.isMoveValid(row, col) == 1) {
//
//                    int initialRow = figure.getRow();
//                    int initialCol = figure.getCol();
//
//                    figure.move(row, col);
//
//                    this.pieceCollection[initialRow][initialCol] = null;
//                    this.pieceCollection[row][col] = this.selectedPiece;
//                    System.out.println(this.pieceCollection[row][col]);
//                }
//
//
//            }
//        }
//    }

    public int isThatFigureYours() {
        Figure figure = (Figure) this.selectedPiece;
        if(figure.getId() < 20 && playerTurn() == 2) {
            System.out.println("Only player 1 can move this");
            return 0;
        } else if(figure.getId() > 20 && playerTurn() == 1) {
            System.out.println("Only player 2 can move this");
            return 0;
        }
        return 1;
    }

    public void mouseClicked(MouseEvent e) {


        int row = this.getBoardDimensionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimensionBasedOnCoordinates(e.getX());

        if(this.selectedPiece != null) {
            figure = (Figure) this.selectedPiece;
                if (figure.isMoveValid(row, col) == 1) {

                    //TODO is button click return 1 otherwise it cant keep going
                    int initialRow = figure.getRow();
                    int initialCol = figure.getCol();
                    if(isTileEmpty(row, col)) {


                        figure.move(row, col);

                        this.pieceCollection[initialRow][initialCol] = null;
                        this.pieceCollection[row][col] = this.selectedPiece;
                    } else if (figure.getId() == 4) {

                        trap = pieceCollection[row][col];
                        figure = (Figure) trap;

                        this.pieceCollection[initialRow][initialCol] = null;
                        this.pieceCollection[row][col] = this.selectedPiece;
                        figure.move(row, col);
                        System.out.println("Trap was destroyed");

                    } else {
                        attack(row, col);
                    }
                    TURN_COUNTER++;
                    sideMenu();
                }

            this.selectedPiece = null;
            repaint();
           return;
        }

        if (this.hasBoardPiece(row, col)) {
            this.selectedPiece = this.getBoardPiece(row, col);
        }

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
        return coordinates / 50;
    }
}
