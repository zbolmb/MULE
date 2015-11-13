package model.landTiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PlainsTile extends MapTiles {
    /**
     * plainsTiles method
     */
    public PlainsTile() {
        super(2, 3, 1, true, false, "Plains"
            , Color.YELLOW, new Image("model/tiles/plains_Tile.jpg"));
    }
}
