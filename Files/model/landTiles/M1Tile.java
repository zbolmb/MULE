package model.landTiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class M1Tile extends MapTiles {
    /**
     * The m1Tile class
     */
    public M1Tile() {
        super(1, 1, 2, true, true, "Mountain 1", Color.web("0xBAA378"),
                new Image("Model/Tiles/m1_Tile.jpg"));
    }
}
