package Piece;

import java.awt.*;

public class Dwarf extends Figure{

    public Dwarf(int row, int col, int id) {
        super(row, col, id);
    }

    public void render(Graphics g) {


        int tileX = this.col * Figure.TILE_SIZE;
        int tileY = this.row * Figure.TILE_SIZE;
        g.setColor(Color.RED);
        g.drawString("D", tileX + 20, tileY + 28);
    }


    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
