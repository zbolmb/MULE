package Model;

import java.util.Random;

import Controller.DisplayContents;
import Model.landTiles.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
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

    public GameMap() {
        dc = Configurations.getDisplayContents();
        this.aMap = new MapTiles[9][5];
    }

    /**
     * Creates the default map for the game.
     * @return The default map
     */
    public void createDefaultGameMap() {

        aMap[0][0] = new plainsTile();
        aMap[1][0] = new plainsTile();
        aMap[2][0] = new m1Tile();
        aMap[3][0] = new plainsTile();
        aMap[4][0] = new riverTile();
        aMap[5][0] = new plainsTile();
        aMap[6][0] = new m3Tile();
        aMap[7][0] = new plainsTile();
        aMap[8][0] = new plainsTile();

        aMap[0][1] = new plainsTile();
        aMap[1][1] = new m1Tile();
        aMap[2][1] = new plainsTile();
        aMap[3][1] = new plainsTile();
        aMap[4][1] = new riverTile();
        aMap[5][1] = new plainsTile();
        aMap[6][1] = new plainsTile();
        aMap[7][1] = new plainsTile();
        aMap[8][1] = new m3Tile();

        aMap[0][2] = new m3Tile();
        aMap[1][2] = new plainsTile();
        aMap[2][2] = new plainsTile();
        aMap[3][2] = new plainsTile();
        aMap[4][2] = new townTile();
        aMap[5][2] = new plainsTile();
        aMap[6][2] = new plainsTile();
        aMap[7][2] = new plainsTile();
        aMap[8][2] = new m1Tile();

        aMap[0][3] = new plainsTile();
        aMap[1][3] = new m2Tile();
        aMap[2][3] = new plainsTile();
        aMap[3][3] = new plainsTile();
        aMap[4][3] = new riverTile();
        aMap[5][3] = new plainsTile();
        aMap[6][3] = new m2Tile();
        aMap[7][3] = new plainsTile();
        aMap[8][3] = new plainsTile();

        aMap[0][4] = new plainsTile();
        aMap[1][4] = new plainsTile();
        aMap[2][4] = new m2Tile();
        aMap[3][4] = new plainsTile();
        aMap[4][4] = new riverTile();
        aMap[5][4] = new plainsTile();
        aMap[6][4] = new plainsTile();
        aMap[7][4] = new plainsTile();
        aMap[8][4] = new m2Tile();
    }

    public void createRandomGameMap() {
        Random rand = new Random();

        for (int i = 0; i < aMap.length; i++) {
            for (int j = 0; j < aMap[0].length; j++) {
                aMap[i][j] = new plainsTile();
            }
        }
        int mType;
        for (int i = 0; i < aMap.length; i++) {
            for (int j = 0; j < aMap[0].length; j++) {
                if (rand.nextInt(3) == 1) {
                    mType = rand.nextInt(3);
                    MapTiles mTypeTile = new m1Tile();
                    if (mType == 0) {
                        mTypeTile = new m1Tile();
                    }
                    if (mType == 1) {
                        mTypeTile = new m2Tile();
                    }
                    if (mType == 2) {
                        mTypeTile = new m3Tile();
                    }
                    aMap[i][j] = mTypeTile;
                }
            }
        }

        for (int i = 0; i < aMap.length; i++) {
            aMap[i][2] = new riverTile();
        }

        aMap[4][2] = new townTile();


    }

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

    public void addMule(int x, int y, int m) {
        if (!dc.getMap().aMap[x][y].getMule(m)) {
            dc.getMap().aMap[x][y].setMule(m);
            Rectangle mule = new Rectangle(x * 100 + 20 + (20 * m), y * 100 + 55, 15, 33);
            Text t = new Text(x * 100 + 20 + (20 * m) + 4, y * 100 + 55 + 25, "F");
            if (m == 0) t.setText("F");
            if (m == 1) t.setText("E");
            if (m == 2) t.setText("O");
            if (Configurations.getCurPlayer().getColor() == Color.BLACK) t.setFill(Color.WHITE);
            mule.setFill(Configurations.getCurPlayer().getColor());
            dc.getMapGUI().getChildren().addAll(mule, t);
        }
    }

    public void updateDC() {
        dc.setMapGUI(getGUI());
        dc.setMap(this);
    }

    public MapTiles[][] getAMap() { return aMap; }
    public void setAMap(MapTiles[][] aMap) { this.aMap = aMap; }
}
