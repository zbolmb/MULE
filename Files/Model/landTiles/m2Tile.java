package model.landTiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class M2Tile extends MapTiles {
    /**
     * The m2Tile class
     */
    public M2Tile() {
        super(1, 1, 3, true, true, "Mountain 2"
            , Color.BROWN, new Image("Model/Tiles/m2_Tile.jpg"));
    }
}
