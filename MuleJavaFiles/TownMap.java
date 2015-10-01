import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Created by willi on 9/28/2015.
 */
public class TownMap {
    protected TownTiles[][] aMap;

    public TownMap() {
        this.aMap = new TownTiles[5][3];
    }

    /**
     * Creates the default map for the game.
     * @return The default map
     */
    public void createDefaultGameMap() {
        aMap[0][0] = new crystiteTownTile();
        aMap[1][0] = new smithoreTownTile();
        aMap[2][0] = new floorTile();
        aMap[3][0] = new energyTownTile();
        aMap[4][0] = new foodTownTile();

        aMap[0][1] = new floorTile();
        aMap[1][1] = new floorTile();
        aMap[2][1] = new floorTile();
        aMap[3][1] = new floorTile();
        aMap[4][1] = new floorTile();

        aMap[0][2] = new assayTownTile();
        aMap[1][2] = new landTownTile();
        aMap[2][2] = new floorTile();
        aMap[3][2] = new pubTile();
        aMap[4][2] = new muleTile();


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
