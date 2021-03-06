package Piece;

import java.awt.*;

public class Knight extends Figure{
    public Knight(int row, int col, int id, int attack, int health, int armor, int rangeAndSpeed) {
        super(row, col, id, attack, health, armor, rangeAndSpeed);
    }
    public void render(Graphics g) {


        int tileX = this.col * Figure.TILE_SIZE;
        int tileY = this.row * Figure.TILE_SIZE;
        g.setColor(Color.RED);
        g.drawString("K", tileX + 20, tileY + 28);
    }

    public int isMoveValid(int row, int col) {
        int x = Math.abs(row - this.row);
        int y = Math.abs(col - this.col);
        if (x+y == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
