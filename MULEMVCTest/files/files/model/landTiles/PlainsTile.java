package files.model.landTiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PlainsTile extends MapTiles {
    /**
     * plainsTiles method
     */
    public PlainsTile() {
        super(2, 3, 1, true, false, "Plains"
            , Color.YELLOW, new Image("files/model/tiles/plains_Tile.jpg"));
    }

    /**
     * constructor for PlainsTile
     * @param a int
     */
    public PlainsTile(int a) {
        super(2, 3, a, true, false, "Plains"
                , Color.YELLOW);
    }
}
