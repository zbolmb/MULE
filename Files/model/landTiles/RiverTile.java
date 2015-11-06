package model.landTiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class RiverTile extends MapTiles {
    /**
     * riverTile method
     */
    public RiverTile() {
        super(4, 2, 0, false, true, "River"
            , Color.BLUE, new Image("model/tiles/river_Tile.jpg"));
    }

}
