package model;

import controller.DisplayContents;
import model.townTiles.TownTiles;
import model.townTiles.assayTownTile;
import model.townTiles.crystiteTownTile;
import model.townTiles.energyTownTile;
import model.townTiles.floorTile;
import model.townTiles.foodTownTile;
import model.townTiles.landTownTile;
import model.townTiles.muleTile;
import model.townTiles.pubTile;
import model.townTiles.smithoreTownTile;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import controller.util;


/**
 * Created by willi on 9/28/2015.
 */
public class TownMap {
    protected static DisplayContents dc;
    protected TownTiles[][] aMap;
    protected Pane pane;

    /**
     * TownMap method
     */
    public TownMap() {
        this.dc = Configurations.getDisplayContents();
        this.aMap = new TownTiles[5][3];
    }

    /**
     * Creates the default map for the game.
     */
    public void createDefaultTownMap() {
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

    /**
     * Scene method
     * @return data
     */
    public Scene getGUI() {
        createDefaultTownMap();
        pane = new Pane();
        Scene scene = new Scene(pane, 800, 500);
        util.addMovementHandlers(scene);
        Rectangle tile;
        for (int i = 0; i < aMap.length; i++) {
            for (int j = 0; j < aMap[0].length; j++) {
                tile = aMap[i][j].getMapTileGui();
                tile.setX(i * tile.getWidth());
                tile.setY(j * tile.getHeight());
                if (!aMap[i][j].getName().equals("Floor")) {
                    Text t = new Text(i * tile.getWidth()
                            + 0.4 * tile.getWidth()
                            , j * tile.getHeight()
                            + 0.5 * tile.getHeight()
                            , aMap[i][j].getName());
                    pane.getChildren().addAll(tile, t);
                } else {
                    pane.getChildren().add(tile);
                }
            }
        }
        return scene;
    }

    /**
     * updateDc method
     */
    public void updateDC() {
        dc.setTownMapGUI(getGUI());
        dc.setTownMap(this);
    }

    /**
     * addPlayer to GUI method
     * @param p player
     */
    public void addPlayerToGUI(Player p) {
        pane.getChildren().add(p.getPlayerIcon());
        dc.getMapGUI().getChildren().remove(p.getPlayerIcon());
    }

    /**
     * removePlayerfromgui mehtod
     * @param p player
     */
    public void removePlayerFromGUI(Player p) {
        pane.getChildren().remove(p.getPlayerIcon());
        dc.getMapGUI().getChildren().add(p.getPlayerIcon());
    }

    /**
     * getAMap method
     * @return aMap
     */
    public TownTiles[][] getAMap() {
        return aMap;
    }

    /**
     * setAMap
     * @param aMap aMap
     */
    public void setAMap(TownTiles[][] aMap) {
        this.aMap = aMap;
    }
}
