package files.model;

import java.util.ArrayList;

import files.model.landTiles.MapTiles;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by William on 9/10/2015.
 * Race: 0 = Elin, 1 = Blood Elves, 2 = Orc, 3 = High Human, 4 = Protoss
 */
public class Player {
	private String name;
	private String race;
	private Color color;
	private int score;
	private Circle playerIcon;
	private ArrayList<MapTiles> owned;
	private double startX;
	private double startY;
	private boolean passed = false;
	private String message;
	/**
	 * phase = 0 when in gameScreen
	 * phase = 1 when in town
	 * phase = 2 when in store
	 */
	private int phase = 0;

	private int money;
	private int food;
	private int energy;
	private int crystite;
	private int smithore;
	private int mule1;
	private int mule2;
	private int mule3;

	/**
	 * Constructor for Player
	 */
	public Player() {
		name = "NoName";
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

	/**
	 * Constructor taking in stuff
	 */
	public Player(String name, String race, Color color) {
		this.name = name;
		this.race = race;
		this.color = color;
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

	/**
	 * Moves the player
	 * @param vx the x velocity
	 * @param vy the y velocity
	 */
	public void move(double vx, double vy) {
		playerIcon.setCenterX(playerIcon.getCenterX() + vx);
		playerIcon.setCenterY(playerIcon.getCenterY() + vy);
	}

	/**
	 * Moves to spot
	 * @param x the x location
	 * @param y the y location
	 */
	public void moveTo(double x, double y) {
		playerIcon.setCenterX(x);
		playerIcon.setCenterY(y);
	}
	/**
	 * setter for x
	 * @param x the x
	 */
	public void setX(double x) {
		playerIcon.setCenterX(x);
	}
	/**
	 * setter for y
	 * @param y the y
	 */
	public void setY(double y) {
		playerIcon.setCenterY(y);
	}
	/**
	 * getter for x
	 * @return double x
	 */
	public double getX() {
		return playerIcon.getCenterX();
	}
	/**
	 * getter for y
	 * @return double y
	 */
	public double getY() {
		return playerIcon.getCenterY();
	}
	/**
	 * setter for name
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter for name
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * getter for race
	 * @return String race
	 */
	public String getRace() {
		return race;
	}
	/**
	 * setter for race
	 * @param race the race
	 */
	public void setRace(String race) {
		this.race = race;
	}
	/**
	 * getter for color
	 * @return Color color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * setter for color
	 * @param color the color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * getter for score
	 * @return int score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * setter for score
	 * @param score the score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * getter for playerIcon
	 * @return Circle playerIcon
	 */
	public Circle getPlayerIcon() {
		return playerIcon;
	}
	/**
	 * setter for playerIcon
	 * @param playerIcon the playerIcon
	 */
	public void setPlayerIcon(Circle playerIcon) {
		this.playerIcon = playerIcon;
	}
	/**
	 * getter for owned
	 * @return ArrayList owned
	 */
	public ArrayList<MapTiles> getOwned() {
		return owned;
	}
	/**
	 * setter for owned
	 * @param owned the owned tiles
	 */
	public void setOwned(ArrayList<MapTiles> owned) {
		this.owned = owned;
	}
	/**
	 * getter for startx
	 * @return double startx
	 */
	public double getStartX() {
		return startX;
	}
	/**
	 * setter for startx
	 * @param startX the startX
	 */
	public void setStartX(double startX) {
		this.startX = startX;
	}
	/**
	 * getter for startY
	 * @return double startY
	 */
	public double getStartY() {
		return startY;
	}
	/**
	 * setter for startY
	 * @param startY the startY
	 */
	public void setStartY(double startY) {
		this.startY = startY;
	}
	/**
	 * getter for isPassed
	 * @return boolean passed
	 */
	public boolean isPassed() {
		return passed;
	}

	/**
	 * setter for isPassed
	 * @param passed the pass
	 */
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	/**
	 * getter for meessage
	 * @return String message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * setter for message
	 * @param message the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * getter for phase
	 * @return int phase
	 */
	public int getPhase() {
		return phase;
	}
	/**
	 * setter for phase
	 * @param phase the phase
	 */
	public void setPhase(int phase) {
		this.phase = phase;
	}
	/**
	 * getter for money
	 * @return int money
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * setter for money
	 * @param money the money
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	/**
	 * getter for food
	 * @return int food
	 */
	public int getFood() {
		return food;
	}
	/**
	 * setter for food
	 * @param food the food
	 */
	public void setFood(int food) {
		this.food = food;
	}
	/**
	 * getter for energy
	 * @return int energy
	 */
	public int getEnergy() {
		return energy;
	}
	/**
	 * setter for energy
	 * @param energy the energy
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	/**
	 * getter for crystite
	 * @return int crystite
	 */
	public int getCrystite() {
		return crystite;
	}
	/**
	 * setter for crystite
	 * @param crystite the crystite
	 */
	public void setCrystite(int crystite) {
		this.crystite = crystite;
	}
	/**
	 * getter for smithore
	 * @return int smithore
	 */
	public int getSmithore() {
		return smithore;
	}
	/**
	 * setter for smithore
	 * @param smithore the smithore
	 */
	public void setSmithore(int smithore) {
		this.smithore = smithore;
	}
	/**
	 * getter for mule1
	 * @return int mule1
	 */
	public int getMule1() {
		return mule1;
	}
	/**
	 * setter for mule1
	 * @param mule1 the mule1
	 */
	public void setMule1(int mule1) {
		this.mule1 = mule1;
	}
	/**
	 * getter for mule2
	 * @return int mule2
	 */
	public int getMule2() {
		return mule2;
	}
	/**
	 * setter for mule2
	 * @param mule2 the mule2
	 */
	public void setMule2(int mule2) {
		this.mule2 = mule2;
	}
	/**
	 * getter for mule3
	 * @return int mule3
	 */
	public int getMule3() {
		return mule3;
	}
	/**
	 * setter for mule3
	 * @param mule3 the mule3
	 */
	public void setMule3(int mule3) {
		this.mule3 = mule3;
	}
}
