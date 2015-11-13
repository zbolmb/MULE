package files.model.landTiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class M2Tile extends MapTiles {
    /**
     * The m2Tile class
     */
    public M2Tile() {
        super(1, 1, 3, true, true, "Mountain 2"
            , Color.BROWN, new Image("files/model/tiles/m2_Tile.jpg"));
    }

    /**
     * constructor for M2Tile
     * @param a int
     */
    public M2Tile(int a) {
        super(a, 1, 3, true, true, "Mountain 2"
                , Color.BROWN);
    }
}
