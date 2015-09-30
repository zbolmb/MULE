import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class util {

    /**
     * method that highlights a tile the color of current player to indicate it is that players land
     * @param x coord of mouse
     * @param y coord of mouse
     * @param c the color to highlight the tile
     * @return the 4 edges of the square (how it highlights)
     */
    public static Rectangle[] drawSelectionSq(double x, double y, Color c) {
        int rx = (int)(x / 100) * 100;
        int ry = (int)(y / 100) * 100;
        Rectangle r = new Rectangle(10, 100, c);
        Rectangle l = new Rectangle(10, 100, c);
        Rectangle u = new Rectangle(100, 10, c);
        Rectangle d = new Rectangle(100, 10, c);
        
        r.setX(rx);
        r.setY(ry);    
        l.setX(rx + 90);
        l.setY(ry);        
        u.setX(rx);
        u.setY(ry);        
        d.setX(rx);
        d.setY(ry + 90);
        
        Rectangle[] ret = new Rectangle[] {r, l, u, d};
        
        return ret;
    }
}
