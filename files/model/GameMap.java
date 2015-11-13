package files.model;

import java.util.Random;

import files.controller.DisplayContents;
import files.model.landTiles.MapTiles;
import files.model.landTiles.M1Tile;
import files.model.landTiles.M2Tile;
import files.model.landTiles.M3Tile;
import files.model.landTiles.PlainsTile;
import files.model.landTiles.RiverTile;
import files.model.landTiles.TownTile;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


/**
 * GAME MAP
 * @author Karl Nicodemus
 *
 * The Game Map is simply a 2-D array of MapTiles.
 *
 */

public class GameMap {

    protected DisplayContents dc;
    protected MapTiles[][] aMap;

    /**
     * gamemap method
     */
    public GameMap() {
        dc = Configurations.getDisplayContents();
        this.aMap = new MapTiles[9][5];
    }

    /**
     * Creates the default map for the game.
     */
    public void createDefaultGameMap() {

        aMap[0][0] = new PlainsTile();
        aMap[1][0] = new PlainsTile();
        aMap[2][0] = new M1Tile();
        aMap[3][0] = new PlainsTile();
        aMap[4][0] = new RiverTile();
        aMap[5][0] = new PlainsTile();
        aMap[6][0] = new M3Tile();
        aMap[7][0] = new PlainsTile();
        aMap[8][0] = new PlainsTile();

        aMap[0][1] = new PlainsTile();
        aMap[1][1] = new M1Tile();
        aMap[2][1] = new PlainsTile();
        aMap[3][1] = new PlainsTile();
        aMap[4][1] = new RiverTile();
        aMap[5][1] = new PlainsTile();
        aMap[6][1] = new PlainsTile();
        aMap[7][1] = new PlainsTile();
        aMap[8][1] = new M3Tile();

        aMap[0][2] = new M3Tile();
        aMap[1][2] = new PlainsTile();
        aMap[2][2] = new PlainsTile();
        aMap[3][2] = new PlainsTile();
        aMap[4][2] = new TownTile();
        aMap[5][2] = new PlainsTile();
        aMap[6][2] = new PlainsTile();
        aMap[7][2] = new PlainsTile();
        aMap[8][2] = new M1Tile();

        aMap[0][3] = new PlainsTile();
        aMap[1][3] = new M2Tile();
        aMap[2][3] = new PlainsTile();
        aMap[3][3] = new PlainsTile();
        aMap[4][3] = new RiverTile();
        aMap[5][3] = new PlainsTile();
        aMap[6][3] = new M2Tile();
        aMap[7][3] = new PlainsTile();
        aMap[8][3] = new PlainsTile();

        aMap[0][4] = new PlainsTile();
        aMap[1][4] = new PlainsTile();
        aMap[2][4] = new M2Tile();
        aMap[3][4] = new PlainsTile();
        aMap[4][4] = new RiverTile();
        aMap[5][4] = new PlainsTile();
        aMap[6][4] = new PlainsTile();
        aMap[7][4] = new PlainsTile();
        aMap[8][4] = new M2Tile();
    }

    /**
     * createRandomGameMap method
     */
    public void createRandomGameMap() {
        Random rand = new Random();

        for (int i = 0; i < aMap.length; i++) {
            for (int j = 0; j < aMap[0].length; j++) {
                aMap[i][j] = new PlainsTile();
            }
        }
        int mType;
        for (int i = 0; i < aMap.length; i++) {
            for (int j = 0; j < aMap[0].length; j++) {
                if (rand.nextInt(3) == 1) {
                    mType = rand.nextInt(3);
                    MapTiles mTypeTile = new M1Tile();
                    if (mType == 0) {
                        mTypeTile = new M1Tile();
                    }
                    if (mType == 1) {
                        mTypeTile = new M2Tile();
                    }
                    if (mType == 2) {
                        mTypeTile = new M3Tile();
                    }
                    aMap[i][j] = mTypeTile;
                }
            }
        }

        for (int i = 0; i < aMap.length; i++) {
            aMap[i][2] = new RiverTile();
        }

        aMap[4][2] = new TownTile();


    }

    /**
     * Pane getGUI method
     * @return data
     */
    public Pane getGUI() {
        createDefaultGameMap();
        //Rectangle tile;
        ImageView tile;
        Pane pane = new Pane();
        for (int i = 0; i < aMap.length; i++) {
            for (int j = 0; j < aMap[0].length; j++) {
                tile = aMap[i][j].getImgView();
                tile.setX(i * 100);
                tile.setY(j * 100);
                pane.getChildren().add(tile);
            }
        }
        return pane;
    }

    /**
     * add Mule method
     * @param x x value
     * @param y y value
     * @param m m value
     */
    public void addMule(int x, int y, int m) {
        if (!dc.getMap().aMap[x][y].getMule(m)) {
            dc.getMap().aMap[x][y].setMule(m);
            Rectangle mule = new Rectangle(x * 100 + 20
                    + (20 * m), y * 100 + 55, 15, 33);
            Text t = new Text(x * 100 + 20 + (20 * m) + 4,
                    y * 100 + 55 + 25, "F");
            if (m == 0) {
                t.setText("F");
            }
            if (m == 1) {
                t.setText("E");
            }
            if (m == 2) {
                t.setText("O");
            }
            if (Configurations.getCurPlayer().getColor() == Color.BLACK
                    || Configurations.getCurPlayer().getColor().toString()
                    .equals("0x000000ff")) {
                t.setFill(Color.WHITE);
            }
            mule.setFill(Configurations.getCurPlayer().getColor());
            dc.getMapGUI().getChildren().addAll(mule, t);
        }
    }

    /**
     * updateDC method
     */
    public void updateDC() {
        dc.setMapGUI(getGUI());
        dc.setMap(this);
    }

    /**
     * MapTiles method
     * @return aMap
     */
    public MapTiles[][] getAMap() {
        return aMap;
    }

    /**
     * setAMap method
     * @param aMap aMap
     */
    public void setAMap(MapTiles[][] aMap) {
        this.aMap = aMap;
    }
}
