package Piece;

import java.awt.*;

public class Figure {
    protected int row;
    protected int col;
    protected int id;
    protected String string;
    public static final int TILE_SIZE = 50;

    public Figure(int row, int col, int id) {
        this.row = row;
        this.col = col;
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void render(Graphics g) {

        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;

    }
}
