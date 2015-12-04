package files.model.townTiles;

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

    /**
     * constructor for Towntiles
     * @param name String
     * @param mapType Color
     */
    public TownTiles(String name, Color mapType) {
        this.mapType = mapType;
        this.name = name;
        this.mapTileGui = new Rectangle(w, h, this.mapType);
    }

    /**
     * gets map type
     * @return Color mapType
     */
    public Color getMapType() {
        return mapType;
    }

    /**
     * sets map type
     * @param mapType Color
     */
    public void setMapType(Color mapType) {
        this.mapType = mapType;
    }

    /**
     * gets name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * gets width
     * @return int width
     */
    public static int getW() {
        return w;
    }

    /**
     * gets height
     * @return int height
     */
    public static int getH() {
        return h;
    }

    //-------------------------------------------------------------------
    /**
     * gets map tile gui
     * @return Rectangle map tile gui
     */
    public Rectangle getMapTileGui() {
        return mapTileGui;
    }

    /**
     * sets map tile gui
     * @param mapTileGui Rectangle
     */
    public void setMapTileGui(Rectangle mapTileGui) {
        this.mapTileGui = mapTileGui;
    }
    //-------------------------------------------------------------------

}
