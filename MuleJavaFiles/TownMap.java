import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Created by willi on 9/28/2015.
 */
public class TownMap {
    protected TownTiles[][] aMap;

    public TownMap() {
        this.aMap = new TownTiles[3][5];
    }

    /**
     * Creates the default map for the game.
     * @return The default map
     */
    public void createDefaultGameMap() {
        aMap[0][0] = new crystiteTownTile();
        aMap[0][1] = new smithoreTownTile();
        aMap[0][2] = new floorTile();
        aMap[0][3] = new energyTownTile();
        aMap[0][4] = new foodTownTile();

        aMap[1][0] = new floorTile();
        aMap[1][1] = new floorTile();
        aMap[1][2] = new floorTile();
        aMap[1][3] = new floorTile();
        aMap[1][4] = new floorTile();

        aMap[2][0] = new assayTownTile();
        aMap[2][1] = new landTownTile();
        aMap[2][2] = new floorTile();
        aMap[2][3] = new pubTile();
        aMap[2][4] = new muleTile();
    }

    public Pane generateMapGui() {
        createDefaultGameMap();
        Rectangle tile;
        Pane pane = new Pane();
        for (int i = 0; i < aMap.length; i++) {
            for (int j = 0; j < aMap[0].length; j++) {
                tile = aMap[i][j].getMapTileGui();
                tile.setX(i * tile.getWidth());
                tile.setY(j * tile.getHeight());
                pane.getChildren().add(tile);
            }
        }
        return pane;
    }

    public TownTiles[][] getAMap() { return aMap; }
    public void setAMap(TownTiles[][] aMap) { this.aMap = aMap; }
}
