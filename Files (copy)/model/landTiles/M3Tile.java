package model.landTiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class M3Tile extends MapTiles {
    /**
     * The m3Tile class
     */
    public M3Tile() {
        super(1, 1, 4, true, true, "Mountain 3"
            , Color.web("0x382E1C"), new Image("model/tiles/m3_Tile.jpg"));
    }
}
