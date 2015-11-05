package Model.landTiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class plainsTile extends MapTiles {
    /**
     * plainsTiles method
     */
    public plainsTile() {
        super(2, 3, 1, true, false, "Plains", Color.YELLOW, new Image("Model/Tiles/plains_Tile.jpg"));
    }
}
