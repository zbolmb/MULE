import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by William on 9/10/2015.
 * Race: 0 = Elin, 1 = Blood Elves, 2 = Orc, 3 = High Human, 4 = Protoss
 */
public class Player {
	protected String name;
	protected String race;
	protected Color color;
	protected int money;
	protected int score;
	protected Circle playerIcon;
	protected ArrayList<MapTiles> owned;

	protected int food;
	protected int energy;
	protected int crystite;
	protected int smithore;
	protected int mule;

	public Player() {
	    name = "No Name";
	    race = "Exile";
	    color = Color.AZURE;
	    money = 1000;
	    owned = new ArrayList<>();
	    score = 0;
		food = 0;
		energy = 0;
		crystite = 0;
		smithore = 0;
		mule = 0;
	}
}
