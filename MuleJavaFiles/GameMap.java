import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * GAME MAP
 * @author Karl Nicodemus
 *
 * The Game Map is simply a 2-D array of MapTiles.
 *
 */

public class GameMap {

	private List<List<MapTiles>> aMap;

	public GameMap() {
		List<ArrayList<MapTiles>> aMap = new ArrayList<ArrayList<MapTiles>>();
	}

	/**
	 * Creates the default map for the game.
	 * @return The default map
	 */
	public ArrayList<ArrayList<MapTiles>> createDefaultGameMap() {

		ArrayList<ArrayList<MapTiles>> returnList = new ArrayList<ArrayList<MapTiles>>(5);

		ArrayList<MapTiles> firstRow = new ArrayList<MapTiles>(9);
		ArrayList<MapTiles> secondRow = new ArrayList<MapTiles>(9);
		ArrayList<MapTiles> thirdRow = new ArrayList<MapTiles>(9);
		ArrayList<MapTiles> fourthRow = new ArrayList<MapTiles>(9);
		ArrayList<MapTiles> fifthRow = new ArrayList<MapTiles>(9);

		firstRow.add(new plainsTile);
		firstRow.add(new plainsTile);
		firstRow.add(new m1Tile);
		firstRow.add(new plainsTile);
		firstRow.add(new riverTile);
		firstRow.add(new plainsTile);
		firstRow.add(new m3Tile);
		firstRow.add(new plainsTile);
		firstRow.add(new plainsTile);

		secondRow.add(new plainsTile);
		secondRow.add(new m1Tile);
		secondRow.add(new plainsTile);
		secondRow.add(new plainsTile);
		secondRow.add(new riverTile);
		secondRow.add(new plainsTile);
		secondRow.add(new plainsTile);
		secondRow.add(new plainsTile);
		secondRow.add(new m3Tile);

		thirdRow.add(new m3Tile);
		thirdRow.add(new plainsTile);
		thirdRow.add(new plainsTile);
		thirdRow.add(new plainsTile);
		thirdRow.add(new townTile);
		thirdRow.add(new plainsTile);
		thirdRow.add(new plainsTile);
		thirdRow.add(new plainsTile);
		thirdRow.add(new m1Tile);

		fourthRow.add(new plainsTile);
		fourthRow.add(new m2Tile);
		fourthRow.add(new plainsTile);
		fourthRow.add(new plainsTile);
		fourthRow.add(new riverTile);
		fourthRow.add(new plainsTile);
		fourthRow.add(new m2Tile);
		fourthRow.add(new plainsTile);
		fourthRow.add(new plainsTile);

		fifthRow.add(new plainsTile);
		fifthRow.add(new plainsTile);
		fifthRow.add(new m2Tile);
		fifthRow.add(new plainsTile);
		fifthRow.add(new riverTile);
		fifthRow.add(new plainsTile);
		fifthRow.add(new plainsTile);
		fifthRow.add(new plainsTile);
		fifthRow.add(new m2Tile);

		returnList.add(firstRow);
		returnList.add(secondRow);
		returnList.add(thirdRow);
		returnList.add(fourthRow);
		returnList.add(fifthRow);

		return returnList;
	}

}
