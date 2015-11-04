package Model;

import java.util.ArrayList;

import Model.landTiles.*;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { 
        return this.name; 
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Circle getPlayerIcon() {
        return playerIcon;
    }

    public void setPlayerIcon(Circle playerIcon) {
        this.playerIcon = playerIcon;
    }

    public ArrayList<MapTiles> getOwned() {
        return owned;
    }

    public void setOwned(ArrayList<MapTiles> owned) {
        this.owned = owned;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getCrystite() {
        return crystite;
    }

    public void setCrystite(int crystite) {
        this.crystite = crystite;
    }

    public int getSmithore() {
        return smithore;
    }

    public void setSmithore(int smithore) {
        this.smithore = smithore;
    }

    public int getMule1() {
        return mule1;
    }

    public void setMule1(int mule1) {
        this.mule1 = mule1;
    }

    public int getMule2() {
        return mule2;
    }

    public void setMule2(int mule2) {
        this.mule2 = mule2;
    }

    public int getMule3() {
        return mule3;
    }

    public void setMule3(int mule3) {
        this.mule3 = mule3;
    }
    
    
    
    
}
