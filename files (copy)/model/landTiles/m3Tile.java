package model.landTiles;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.landTiles.MapTiles;

public class m3Tile extends MapTiles {
	public m3Tile() {
		super(1, 1, 4, true, true, "Mountain 3", Color.web("0x382E1C"), new Image("Model/Tiles/m3_Tile.jpg"));
	}
}
