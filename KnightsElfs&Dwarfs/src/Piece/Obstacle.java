package Piece;

import java.awt.*;

public class Obstacle extends Figure {
    public static final int TILE_SIZE = 50;

    public Obstacle(int row, int col, int id) {
        super(row, col, id, 0 ,0 ,0 ,0);
    }

    public void render(Graphics g) {
        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;

        g.setColor(Color.black);
        g.fillRect(tileX, tileY, this.TILE_SIZE, this.TILE_SIZE);
    }
}
