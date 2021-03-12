package Piece;

import java.awt.*;

public class Figure {
    protected int row;
    protected int col;
    protected int id;
    protected String string;
    protected int attack;
    protected int health;
    protected int armor;
    protected int rangeAndSpeed;
    public static final int TILE_SIZE = 50;

    public Figure(int row, int col, int id, int attack, int health, int armor, int rangeAndSpeed) {
        this.row = row;
        this.col = col;
        this.id = id;
        this.attack = attack;
        this.health = health;
        this.armor = armor;
        this.rangeAndSpeed = rangeAndSpeed;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getId() {
        return id;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public String getString() {
        return string;
    }

    public int getRangeAndSpeed() {
        return rangeAndSpeed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void render(Graphics g) {

        int tileX = this.col * TILE_SIZE;
        int tileY = this.row * TILE_SIZE;

        g.setColor(Color.RED);
        g.drawString("E", tileX + 20, tileY + 28);

    }



    public int isMoveValid(int row, int col) {
        return 1;
    }
}
