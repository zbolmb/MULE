package files.model.landTiles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * An abstract class regarding MapTiles
 * @author Karl Nicodemus
 *
 */

public abstract class MapTiles {

    private String owner;
    private boolean[] mules;

    private int food;
    private int energy;
    private int ore;
    private boolean crystite;
    private boolean highlightable;
    private String name;
    private Image img;


    //----------Should replace this with an image of tile--------------
    private Rectangle mapTileGui;
    private static int w = 100;
    private static int h = 100;
    private Color mapType;
    //-----------------------------------------------------------------




    /**
     * The map tile class
     * @param food the food generated
     * @param energy the energy generated
     * @param ore the ore generated
     * @param crystite whether crystite is present
     * @param highlightable if it is highlitable
     * @param mapType ** This is temporary. Should be an image **
     * @param name The name of the tile
     * @param img The image of the tile
     *
     */
    public MapTiles(int food, int energy, int ore, boolean crystite,
                    boolean highlightable, String name,
                    Color mapType, Image img) {
        this.food = food;
        this.energy = energy;
        this.ore = ore;
        this.crystite = crystite;
        this.highlightable = highlightable;
        this.mapTileGui = new Rectangle(w, h, mapType);
        this.mapType = mapType;
        this.owner = "None";
        this.name = name;
        this.img = img;
        this.mules = new boolean[3];
    }

    /**
     * The map tile class
     * @param food the food generated
     * @param energy the energy generated
     * @param ore the ore generated
     * @param crystite whether crystite is present
     * @param highlightable if it is highlitable
     * @param mapType ** This is temporary. Should be an image **
     * @param name The name of the tile
     *
     */
    public MapTiles(int food, int energy, int ore, boolean crystite,
                    boolean highlightable, String name,
                    Color mapType) {
        this.food = food;
        this.energy = energy;
        this.ore = ore;
        this.crystite = crystite;
        this.highlightable = highlightable;
        this.mapTileGui = new Rectangle(w, h, mapType);
        this.mapType = mapType;
        this.owner = "None";
        this.name = name;
        this.mules = new boolean[3];
    }
    /**
    * Getters and Setters
    * @return various
    */
    public int getFood() {
        return food;
    }

    /**
     * SetFood
     * @param food set the food
     */
    public void setFood(int food) {
        this.food = food;
    }

    /**
     * get energy
     * @return the energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * set energy
     * @param energy takes in energy
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * get the ore
     * @return the ore
     */
    public int getOre() {
        return ore;
    }

    /**
     * set ore
     * @param ore takes in the ore
     */
    public void setOre(int ore) {
        this.ore = ore;
    }

    /**
     * checks if crystalite
     * @return the crystite
     */
    public boolean isCrystalite() {
        return crystite;
    }

    /**
     * set crystite
     * @param crystite takes in crystite
     */
    public void setCrystalite(boolean crystite) {
        this.crystite = crystite;
    }

    /**
     * checks if highlightable
     * @return return boolean
     */
    public boolean isHighlightable() {
        return highlightable;
    }

    /**
     * sethighlightable
     * @param highlightable get whether it is highlightable
     */
    public void setHighlightable(boolean highlightable) {
        this.highlightable = highlightable;
    }

    /**
     * getmaptype method
     * @return maptype
     */
    public Color getMapType() {
        return mapType;
    }

    /**
     * get maptype
     * @param mapType takes in maptype
     */
    public void getMapType(Color mapType) {
        this.mapType = mapType;
    }

    /**
     * getName
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getW
     * @return the w
     */
    public static int getW() {
        return w;
    };

    /**
     * getH
     * @return the h
     */
    public static int getH() {
        return h;
    };

    /**
     * getMapTileGui
     * @return maptileGui
     */
    public Rectangle getMapTileGui() {
        return mapTileGui;
    }

    /**
     * setMapTileGui
     * @param mapTileGui mapTileGui
     */
    public void setMapTileGui(Rectangle mapTileGui) {
        this.mapTileGui = mapTileGui;
    };

    /**
     * getOwner function
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * setOwner function
     * @param owner the owner param
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * getImg method
     * @return img img
     */
    public Image getImg() {
        return img;
    }

    /**
     * setImg
     * @param img takes in img
     */
    public void setImg(Image img) {
        this.img = img;
    }

    /**
     * getimgview
     * @return img
     */
    public ImageView getImgView() {
        return new ImageView(img);
    }

    /**
     * getMule method
     * @param m m
     * @return mule
     */
    public boolean getMule(int m) {
        return mules[m];
    }

    /**
     * setMule method
     * @param m m
     */
    public void setMule(int m) {
        mules[m] = true;
    }

    /**
     * get mules
     * @return mules
     */
    public boolean[] getMules() {
        return mules;
    }

    /**
     * set mules
     * @param mules boolean array
     */
    public void setMules(boolean[] mules) {
        this.mules = mules;
    }
}
