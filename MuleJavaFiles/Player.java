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
	protected Circle playerIcon;

	public Player() {
	    name = "noName";
	    race = "Exile";
	    color = Color.AZURE;
	    money = 0;
	}
}
