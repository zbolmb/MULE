package model.landTiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class townTile extends MapTiles{
    /**
     * townTile method
     */
    public townTile() {
        super(0, 0, 0, false, false, "Town", Color.BLACK, new Image("Model/Tiles/town_Tile.jpg"));
    }
}
