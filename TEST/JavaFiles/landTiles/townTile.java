package landTiles;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class townTile extends MapTiles{
	public townTile() {
	    super(0, 0, 0, false, false, "Town", Color.BLACK, new Image("Tiles/town_Tile.jpg"));
	}
}
