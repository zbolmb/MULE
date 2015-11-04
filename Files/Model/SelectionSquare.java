package Model;

import Model.landTiles.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class SelectionSquare {
    protected Rectangle[] sq;
    
    public SelectionSquare() {
        sq = new Rectangle[4];
        sq[0] = new Rectangle(10, 100, Color.WHITE);
        sq[1] = new Rectangle(10, 100, Color.WHITE);
        sq[2] = new Rectangle(100, 10, Color.WHITE);
        sq[3] = new Rectangle(100, 10, Color.WHITE);
        moveSelection(0, 0);
    }
    public void moveSelection(double x, double y) {
        int rx = (int)(x / 100) * 100;
        int ry = (int)(y / 100) * 100;
        sq[0].setX(rx);
        sq[0].setY(ry);
        
        sq[1].setX(rx + 90);
        sq[1].setY(ry);
        
        sq[2].setX(rx);
        sq[2].setY(ry);
        
        sq[3].setX(rx);
        sq[3].setY(ry + 90);
    }
    
    public void remove() {
        for (Rectangle r : sq) {
            r.setX(-100);
            r.setY(-100);
        }
    }
    
    public MapTiles getTile(double x, double y, GameMap map) {
        int rx = (int)(x / 100);
        int ry = (int)(y / 100);
        return map.aMap[rx][ry];
    }
}
