import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


/**
 * GAME MAP
 * @author Karl Nicodemus
 *
 * The Game Map is simply a 2-D array of MapTiles.
 *
 */

public class GameMap {
    
	protected MapTiles[][] aMap;

	public GameMap() {
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


		//
		//		ArrayList<ArrayList<MapTiles>> returnList = new ArrayList<ArrayList<MapTiles>>(5);
		//
		//		ArrayList<MapTiles> firstRow = new ArrayList<MapTiles>(9);
		//		ArrayList<MapTiles> secondRow = new ArrayList<MapTiles>(9);
		//		ArrayList<MapTiles> thirdRow = new ArrayList<MapTiles>(9);
		//		ArrayList<MapTiles> fourthRow = new ArrayList<MapTiles>(9);
		//		ArrayList<MapTiles> fifthRow = new ArrayList<MapTiles>(9);
		//
		//		firstRow.add(new plainsTile());
		//		firstRow.add(new plainsTile());
		//		firstRow.add(new m1Tile());
		//		firstRow.add(new plainsTile());
		//		firstRow.add(new riverTile());
		//		firstRow.add(new plainsTile());
		//		firstRow.add(new m3Tile());
		//		firstRow.add(new plainsTile());
		//		firstRow.add(new plainsTile());
		//
		//		secondRow.add(new plainsTile());
		//		secondRow.add(new m1Tile());
		//		secondRow.add(new plainsTile());
		//		secondRow.add(new plainsTile());
		//		secondRow.add(new riverTile());
		//		secondRow.add(new plainsTile());
		//		secondRow.add(new plainsTile());
		//		secondRow.add(new plainsTile());
		//		secondRow.add(new m3Tile());
		//
		//		thirdRow.add(new m3Tile());
		//		thirdRow.add(new plainsTile());
		//		thirdRow.add(new plainsTile());
		//		thirdRow.add(new plainsTile());
		//		thirdRow.add(new townTile());
		//		thirdRow.add(new plainsTile());
		//		thirdRow.add(new plainsTile());
		//		thirdRow.add(new plainsTile());
		//		thirdRow.add(new m1Tile());
		//
		//		fourthRow.add(new plainsTile());
		//		fourthRow.add(new m2Tile());
		//		fourthRow.add(new plainsTile());
		//		fourthRow.add(new plainsTile());
		//		fourthRow.add(new riverTile());
		//		fourthRow.add(new plainsTile());
		//		fourthRow.add(new m2Tile());
		//		fourthRow.add(new plainsTile());
		//		fourthRow.add(new plainsTile());
		//
		//		fifthRow.add(new plainsTile());
		//		fifthRow.add(new plainsTile());
		//		fifthRow.add(new m2Tile());
		//		fifthRow.add(new plainsTile());
		//		fifthRow.add(new riverTile());
		//		fifthRow.add(new plainsTile());
		//		fifthRow.add(new plainsTile());
		//		fifthRow.add(new plainsTile());
		//		fifthRow.add(new m2Tile());
		//
		//		returnList.add(firstRow);
		//		returnList.add(secondRow);
		//		returnList.add(thirdRow);
		//		returnList.add(fourthRow);
		//		returnList.add(fifthRow);
		//
		//		this.aMap = returnList;

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

	public MapTiles[][] getAMap() { return aMap; }
	public void setAMap(MapTiles[][] aMap) { this.aMap = aMap; }
}
