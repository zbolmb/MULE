package model;

import controller.DisplayContents;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by William on 9/10/2015.
 */
public class Configurations {
	private static String difficulty = "Easy";
	private static String map_Type = "River";
	private static int num_Players = 1;
	private static DisplayContents displayContents;
	private static Player curPlayer;
	
	/**
	 * -2 = land grant. Two rounds
	 *  0 = buy phase
	 *  1 = first round
	 *  2...n = round n
	 */
	private static int round = -3;
	
	/**
	 * 0 = setting up
	 * 1 = move phase/player turn
	 */
	private static int phase = 0;

	private static ArrayList<Player> players = new ArrayList<>();
	
	public static void initDC(Stage primaryStage) {
	    displayContents = new DisplayContents(primaryStage);
	}

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        Configurations.difficulty = difficulty;
    }

    public static String getMap_Type() {
        return map_Type;
    }

    public static void setMap_Type(String map_Type) {
        Configurations.map_Type = map_Type;
    }

    public static int getNum_Players() {
        return num_Players;
    }

    public static void setNum_Players(int num_Players) {
        Configurations.num_Players = num_Players;
    }

    public static DisplayContents getDisplayContents() {
        return displayContents;
    }

    public static void setDisplayContents(DisplayContents displayContents) {
        Configurations.displayContents = displayContents;
    }

    public static Player getCurPlayer() {
        return curPlayer;
    }

    public static void setCurPlayer(Player curPlayer) {
        Configurations.curPlayer = curPlayer;
    }

    public static int getRound() {
        return round;
    }

    public static void setRound(int round) {
        Configurations.round = round;
    }

    public static int getPhase() {
        return phase;
    }

    public static void setPhase(int phase) {
        Configurations.phase = phase;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        Configurations.players = players;
    }
	
	

}
