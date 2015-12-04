package files.model;

import files.model.townTiles.TownTiles;
import files.model.townTiles.AssayTownTile;
import files.model.townTiles.CrystiteTownTile;
import files.model.townTiles.EnergyTownTile;
import files.model.townTiles.FloorTile;
import files.model.townTiles.FoodTownTile;
import files.model.townTiles.LandTownTile;
import files.model.townTiles.MuleTile;
import files.model.townTiles.PubTile;
import files.model.townTiles.SmithoreTownTile;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import files.controller.Util;


/**
 * Created by willi on 9/28/2015.
 */
public class TownMap {
    protected TownTiles[][] aMap;
    protected Pane pane;

    /**
     * TownMap method
     */
    public TownMap() {
        this.aMap = new TownTiles[5][3];
    }

    /**
     * getter for TownTiles
     * @return TownTiles the townTiles
     */
    public TownTiles[][] getAMap() {
        return aMap;
    }

    /**
     * setter for townTiles
     * @param aMap the map to set
     */
    public void setAMap(TownTiles[][] aMap) {
        this.aMap = aMap;
    }
    
    
}
