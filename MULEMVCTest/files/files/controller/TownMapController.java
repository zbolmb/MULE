package files.controller;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import files.model.Player;
import files.model.TownMap;
import files.model.townTiles.AssayTownTile;
import files.model.townTiles.CrystiteTownTile;
import files.model.townTiles.EnergyTownTile;
import files.model.townTiles.FloorTile;
import files.model.townTiles.FoodTownTile;
import files.model.townTiles.LandTownTile;
import files.model.townTiles.MuleTile;
import files.model.townTiles.PubTile;
import files.model.townTiles.SmithoreTownTile;
import files.model.townTiles.TownTiles;
import files.view.TownMapView;

public class TownMapController {

    TownMap map;
    TownMapView mapView;
    
    public TownMapController() {
        map = new TownMap();
        mapView = new TownMapView(new Pane());
    }
    

    /**
     * Creates the default map for the game.
     */
    public void createDefaultTownMap() {
        TownTiles[][] aMap = new TownTiles[5][3];
        aMap[0][0] = new CrystiteTownTile();
        aMap[1][0] = new SmithoreTownTile();
        aMap[2][0] = new FloorTile();
        aMap[3][0] = new EnergyTownTile();
        aMap[4][0] = new FoodTownTile();

        aMap[0][1] = new FloorTile();
        aMap[1][1] = new FloorTile();
        aMap[2][1] = new FloorTile();
        aMap[3][1] = new FloorTile();
        aMap[4][1] = new FloorTile();

        aMap[0][2] = new AssayTownTile();
        aMap[1][2] = new LandTownTile();
        aMap[2][2] = new FloorTile();
        aMap[3][2] = new PubTile();
        aMap[4][2] = new MuleTile();
        map.setAMap(aMap);
    }
    
    /**
     * updates View
     */
    public void updateView() {
        createDefaultTownMap();
        Util.addMovementHandlers(mapView);
        TownTiles[][] aMap = map.getAMap();
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
                    mapView.add(tile);
                    mapView.add(t);
                } else {
                    mapView.add(tile);
                }
            }
        }
    }

    /**
     * adds to the mapView
     */
    public void add(Node n) {
        mapView.add(n);
    }
    
    /**
     * remove from mapView
     */
    public void remove(Node n) {
        mapView.remove(n);
    }
    
    /**
     * getAMap method
     * @return aMap
     */
    public TownTiles[][] getAMap() {
        return map.getAMap();
    }

    /**
     * setAMap
     * @param aMap aMap
     */
    public void setAMap(TownTiles[][] aMap) {
        map.setAMap(aMap);
    }

    /**
     * getter for view
     * @return TownMapView pane
     */
    public TownMapView getView() {
        return mapView;
    }
}
