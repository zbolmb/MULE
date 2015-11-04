import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by William on 9/10/2015.
 */
public class Configurations {
	protected static String difficulty;
	protected static String map_Type;
	protected static int num_Players;
	protected static DisplayContents displayContents;
	protected static Player curPlayer;
	
	/**
	 * -2 = land grant. Two rounds
	 *  0 = buy phase
	 *  1 = first round
	 *  2...n = round n
	 */
	protected static int round = -3;
	
	/**
	 * 0 = setting up
	 * 1 = move phase/player turn
	 */
	protected static int phase = 0;

	protected static ArrayList<Player> players;

	public Configurations(Stage primaryStage) {
		players = new ArrayList<>();
		difficulty = "Easy";
		map_Type = "River";
		num_Players = 1;
		displayContents = new DisplayContents(primaryStage);
	}

	public Configurations(String difficulty, String map_Type, int num_Players) {
		players = new ArrayList<>();
		this.difficulty = difficulty;
		this.map_Type = map_Type;
		this.num_Players = num_Players;
	}

}
