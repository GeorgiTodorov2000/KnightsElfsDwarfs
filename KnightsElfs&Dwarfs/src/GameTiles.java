import java.awt.*;

public class GameTiles {
    public static final int TILE_SIZE = 50;


    private int row;
    private int col;



    public GameTiles(int row, int col) {
        this.row = row;
        this.col = col;

    }

    public void render(Graphics g) {
        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;

        g.drawRect(50, 50, 450, 350);
        g.drawRect(50, 100, 450, 250);
        g.drawRect(50, 150, 450, 150);
        g.drawRect(50, 200, 450, 50);
        g.drawRect(100, 50, 350, 350);
        g.drawRect(150, 50, 250, 350);
        g.drawRect(200, 50, 150, 350);
        g.drawRect(250, 50, 50, 350);

        if(row == 1 && col % 2 == 0) {
            g.setColor(new Color(68,68,68));
        } else if (row == 1 && col % 2 == 1){g.setColor(new Color(170,170,170));}

        if(row == 2 && col % 2 == 1) {
            g.setColor(new Color(68,68,68));
        } else if ((row == 2 && col % 2 == 0)){g.setColor(new Color(170,170,170));}

        if(row == 3 || row == 4 || row == 5) {
            g.setColor(new Color(214,214,214));
        }

        if(row == 6 && col % 2 == 1) {
            g.setColor(new Color(68,68,68));
        } else if ((row == 6 && col % 2 == 0)){g.setColor(new Color(170,170,170));}

        if(row == 7 && col % 2 == 0) {
            g.setColor(new Color(68,68,68));
        } else if ((row == 7 && col % 2 == 1)){g.setColor(new Color(170,170,170));}

        g.fillRect(tileX, tileY, this.TILE_SIZE, this.TILE_SIZE);
    }
}
