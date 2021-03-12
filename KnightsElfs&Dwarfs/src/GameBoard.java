import Piece.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Scanner;

public class GameBoard extends JFrame implements MouseListener {
    public static final int TILE_X_COUNT = 9;
    public static final int TILE_Y_COUNT = 11;
    private Object[][] pieceCollection;
    private Object selectedPiece;
    private Object attackPiece;
    private Random RANDOM = new Random();
    private int TURN_COUNTER = 1;
    private Object trap;
    private int OBSTACLE_CLICK_COUNT = 0;
    private int p1FigureCount = 6;
    private int p2FigureCount = 6;

    JLabel l1 = null;

    public GameBoard() {
        this.pieceCollection = new Object[TILE_X_COUNT][TILE_Y_COUNT];
        RANDOM = new Random();

        createObstacles();
        pieceCreator();



        this.setSize(750, 450);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);
    }

    public void sideMenu(Figure figure) {
        if(l1 != null) {
            this.remove(l1);
        }
        String currentTurn;
        if(playerTurn() == 1) {
            currentTurn = "Player 2";
        } else {
            currentTurn = "Player 1";
        }
        l1=new JLabel(currentTurn);
        l1.setBounds(544,20, 100,30);
        JButton attack=new JButton("ATTACK!");
        JButton move=new JButton("Move");
        JButton heal=new JButton("Heal");

//        attack.addActionListener(e ->
//        {
//            attack(row,col);
//        });
        heal.addActionListener(e -> {
            heal(figure);
        });
//        move.addActionListener(e -> {
//            moveFigure(row, col, figure);
//        });

        attack.setBounds(520,50,95,30);
        move.setBounds(520,100,95,30);
        heal.setBounds(520,150,95,30);

        this.add(l1);this.add(attack);this.add(move);this.add(heal);
        this.setLayout(null);
        this.setVisible(true);
    }

//    private int buttonAttack(
//            int row, int col, Figure figure
//    ) {
//        JButton attack=new JButton("ATTACK!");
//
//        attack.setBounds(520,50,95,30);
//
//        return 1;
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
//        Scanner in = new Scanner(System.in);

//        for(int pieceCount = 0; pieceCount < 13; pieceCount++) {
//            if (pieceCount < 2) {
//                System.out.println("Coordinates X for Elf Player 1");
//                int x = in.nextInt();
//                System.out.println("Coordinates Y for Elf Player 1");
//                int y = in.nextInt();
//            } else if (pieceCount < 4){
//                System.out.println("Coordinates X for Dwarf Player 1");
//                int x = in.nextInt();
//                System.out.println("Coordinates Y for Dwarf Player 1");
//                int y = in.nextInt();
//            } else if (pieceCount < 6) {
//                System.out.println("Coordinates X for Knight Player 1");
//                int x = in.nextInt();
//                System.out.println("Coordinates Y for Knight Player 1");
//                int y = in.nextInt();
//            } else if (pieceCount < 8) {
//                System.out.println("Coordinates X for Elf Player 2");
//                int x = in.nextInt();
//                System.out.println("Coordinates Y for Elf Player 2");
//                int y = in.nextInt();
//            } else if (pieceCount < 10){
//                System.out.println("Coordinates X for Dwarf Player 2");
//                int x = in.nextInt();
//                System.out.println("Coordinates Y for Dwarf Player 2");
//                int y = in.nextInt();
//            } else if (pieceCount < 12) {
//                System.out.println("Coordinates X for Knight Player 2");
//                int x = in.nextInt();
//                System.out.println("Coordinates Y for Knight Player 2");
//                int y = in.nextInt();
//            }
//        }


        this.pieceCollection[1][1] = new Elf(1,1,11, 5, 10, 1, 3);
        this.pieceCollection[1][3] = new Dwarf(1,3, 12,6, 12, 2, 2);
        this.pieceCollection[1][5] = new Knight(1,5,13, 8 , 15, 3, 1);
        this.pieceCollection[7][5] = new Elf(7,5,21, 5, 10, 1, 3);
        this.pieceCollection[7][7] = new Dwarf(7,7, 22,6, 12, 2, 2);
        this.pieceCollection[7][9] = new Knight(7,9,23, 8 , 15, 3, 1);
        this.pieceCollection[2][3] = new Elf(2,3,14, 5, 10, 1, 3);
        this.pieceCollection[2][5] = new Dwarf(2,5, 15,6, 12, 2, 2);
        this.pieceCollection[2][6] = new Knight(2,6,16, 8 , 15, 3, 1);
        this.pieceCollection[6][1] = new Elf(6,1,24, 5, 10, 1, 3);
        this.pieceCollection[6][3] = new Dwarf(6,3, 25,6, 12, 2, 2);
        this.pieceCollection[6][5] = new Knight(6,5,26, 8 , 15, 3, 1);
    }

    private boolean isTileEmpty(int row, int col) {
        return this.pieceCollection[row][col] == null;
    }




    public boolean attack (int row, int col, Figure figure, Figure attacked) {
        this.pieceCollection[row][col] = this.attackPiece;

        int defence = attacked.getArmor();
        int health = attacked.getHealth();
        int finalHealth = health - (figure.getAttack() - defence);
        if(finalHealth <= 0) {
            this.pieceCollection[row][col] = null;
            lost(attacked);
            System.out.println("Enemy figure died");
        } else {
            this.pieceCollection[row][col] = this.attackPiece;
            System.out.println("Enemy figure survived with hp: " + finalHealth);
            attacked.setHealth(finalHealth);
        }
        return false;
    }

    private void lost(Figure attacked) {
        if (attacked.getId() > 20) {
            p2FigureCount--;
            if(p2FigureCount == 0) {
                System.out.println("Player 2 lost");
                System.exit(1);
            }
        } else {
            p1FigureCount--;
            if(p1FigureCount == 0) {
                System.out.println("Player 1 lost");
                System.exit(1);
            }
        }
    }

    public void heal(Figure figure) {

        int healing = RANDOM.nextInt(6);
        ++healing;

        figure.setHealth(figure.getHealth()+healing);
        if (healing % 2 != 1) {
            TURN_COUNTER++;
        }
        System.out.println(healing);
    }

//    private boolean moveFigure(int row, int col, Figure figure) {
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
//        return false;
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

    private boolean isTileObstacle(int row, int col) {

        Figure figure = (Figure) this.pieceCollection[row][col];
        return figure.getId() == 4;
    }



    public void mouseClicked(MouseEvent e) {


        int row = this.getBoardDimensionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimensionBasedOnCoordinates(e.getX());

        if(this.selectedPiece != null) {

            Figure figure = (Figure) this.selectedPiece;
            if (this.hasBoardPiece(row, col)) {
                this.attackPiece = this.getBoardPiece(row, col);
            }
            Figure attacked = (Figure) this.attackPiece;

            if (isThatFigureYours() == 1) {
                if (figure.isMoveValid(row, col) == 1) {

                    int initialRow = figure.getRow();
                    int initialCol = figure.getCol();
                    if (isTileEmpty(row, col)) {


                        figure.move(row, col);

                        this.pieceCollection[initialRow][initialCol] = null;
                        this.pieceCollection[row][col] = this.selectedPiece;
                    } else if (isTileObstacle(row, col)) {
                        System.out.println("This is obstacle, if clicked again it will be destroyed!");
                        if (OBSTACLE_CLICK_COUNT % 2 == 1) {
                            figure = (Figure) this.pieceCollection[initialRow][initialCol];
                            figure.move(initialRow, initialCol);
                            this.pieceCollection[initialRow][initialCol] = null;
                            this.pieceCollection[row][col] = this.selectedPiece;

                            System.out.println("Trap was destroyed");
                        } else {
                            OBSTACLE_CLICK_COUNT++;
                            return;
                        }
                    }  else{
                        if (figure.getId() < 20 && attacked.getId() > 20) {
                            attack(row, col, figure, attacked);
                        }
                        else if (figure.getId() > 20 && attacked.getId() < 20) {

                            attack(row, col, figure, attacked);
                        }
                        else {
                            System.out.println("You can't attack your own figure");
                            return;
                        }
                    }

            } else if(figure.getCol() == col && figure.getRow() == row) {
                    heal(figure);

                }
                TURN_COUNTER++;
                sideMenu(figure);
                this.attackPiece = null;
            this.selectedPiece = null;
            repaint();
           return;
        }}

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
