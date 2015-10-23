import java.util.ArrayList;

import landTiles.*;
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
	protected int score;
	protected Circle playerIcon;
	protected ArrayList<MapTiles> owned;
	protected double startX;
	protected double startY;
	protected boolean passed = false;
	protected String message;
	/**
	 * phase = 0 when in gameScreen
	 * phase = 1 when in town
	 * phase = 2 when in store
	 */
	protected int phase = 0;
	
    protected int money;
	protected int food;
	protected int energy;
	protected int crystite;
	protected int smithore;
	protected int mule1;
	protected int mule2;
	protected int mule3;

	public Player() {
	    name = "No Name";
	    race = "Exile";
	    color = Color.BLACK;
	    money = 1000;
	    owned = new ArrayList<>();
	    score = 0;
		food = 0;
		energy = 4;
		crystite = 0;
		smithore = 0;
		mule1 = 0;
		mule2 = 0;
		mule3 = 0;
		message = "";
	}

    public void move(double vx, double vy) {
        playerIcon.setCenterX(playerIcon.getCenterX() + vx);
        playerIcon.setCenterY(playerIcon.getCenterY() + vy);
    }
    
    public void moveTo(double x, double y) {
        playerIcon.setCenterX(x);
        playerIcon.setCenterY(y);
    }
    
    public void setX(double x) {
        playerIcon.setCenterX(x);
    }
    
    public void setY(double y) {
        playerIcon.setCenterY(y);
    }
    
    public double getX() {
        return playerIcon.getCenterX();
    }
    
    public double getY() {
        return playerIcon.getCenterY();
    }
}
