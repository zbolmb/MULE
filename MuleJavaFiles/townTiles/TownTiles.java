package townTiles;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Created by willi on 9/28/2015.
 */
public abstract class TownTiles {

    private String name;

    //----------Should replace this with an image of tile--------------
    private Rectangle mapTileGui;
    private static int w = 160;
    private static int h = 166;
    private Color mapType;
    //-----------------------------------------------------------------


    public TownTiles(String name, Color mapType) {
        this.mapType = mapType;
        this.name = name;
        this.mapTileGui = new Rectangle(w, h, this.mapType);
    }

    /**
     * Getters and Setters
     * @return various
     */

    public Color getMapType() { return mapType; }
    public void getMapType(Color mapType) { this.mapType = mapType; }
    public String getName() { return name; }
    public static int getW() { return w; };
    public static int getH() { return h; };
    //-------------------------------------------------------------------
    public Rectangle getMapTileGui() { return mapTileGui;}
    public void setMapTileGui(Rectangle mapTileGui) { this.mapTileGui = mapTileGui; };
    //-------------------------------------------------------------------

}
