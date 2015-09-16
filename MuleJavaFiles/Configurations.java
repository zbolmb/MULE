import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by William on 9/10/2015.
 * Difficulty: 0 = easy, 1 = medium, 2 = hard
 * Map Type: 0 = river, 1 = mountain, 2 = plain
 */
public class Configurations {
	protected String difficulty;
	protected String map_Type;
	protected int num_Players;

	protected ArrayList<Player> players;

	public Configurations() {
		players = new ArrayList<>();
		difficulty = "Easy";
		map_Type = "River";
		num_Players = 1;
	}

}
