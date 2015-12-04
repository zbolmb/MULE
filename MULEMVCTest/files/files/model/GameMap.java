package files.model;

import java.util.Random;

import files.model.landTiles.MapTiles;
import files.model.landTiles.M1Tile;
import files.model.landTiles.M2Tile;
import files.model.landTiles.M3Tile;
import files.model.landTiles.PlainsTile;
import files.model.landTiles.RiverTile;
import files.model.landTiles.TownTile;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


/**
 * GAME MAP
 * @author Karl Nicodemus
 *
 * The Game Map is simply a 2-D array of MapTiles.
 *
 */

public class GameMap {

    protected MapTiles[][] aMap;

    /**
     * gamemap method
     */
    public GameMap() {
        this.aMap = new MapTiles[9][5];
    }

    /**
     * MapTiles method
     * @return aMap
     */
    public MapTiles[][] getAMap() {
        return aMap;
    }

    /**
     * setAMap method
     * @param aMap aMap
     */
    public void setAMap(MapTiles[][] aMap) {
        this.aMap = aMap;
    }
}
