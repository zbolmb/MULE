package model.townTiles;

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
     * Constructor
     * @param name the name
     * @param mapType the mapType
     */
    public TownTiles(String name, Color mapType) {
        this.mapType = mapType;
        this.name = name;
        this.mapTileGui = new Rectangle(w, h, this.mapType);
    }

    /**
     * getter for color
     * @return Color the color
     */
    public Color getMapType() {
        return mapType;
    }

    /**
     * getter for mapType
     * @param mapType the mapType
     */
    public void setMapType(Color mapType) {
        this.mapType = mapType;
    }

    /**
     * getter for name
     * @return String the name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for w
     * @return int the w
     */
    public static int getW() {
        return w;
    }

    /**
     * getter for h
     * @return int the h
     */
    public static int getH() {
        return h;
    }
    //-------------------------------------------------------------------
    /**
     * getter for mapTileGui
     * @return Rectangle the mapTileGui
     */
    public Rectangle getMapTileGui() {
        return mapTileGui;
    }

    /**
     * setter for mapTileGui
     * @param mapTileGui the mapTileGui to set
     */
    public void setMapTileGui(Rectangle mapTileGui) {
        this.mapTileGui = mapTileGui;
    }
    //-------------------------------------------------------------------

}
