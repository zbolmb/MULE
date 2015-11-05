package Model;

import Controller.DisplayContents;
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

    /**
     * initDC initializes DC
     * @param primaryStage Stage, the primary window
     */
    public static void initDC(Stage primaryStage) {
        displayContents = new DisplayContents(primaryStage);
    }

    /**
     * getDifficulty returns difficulty
     * @return String the difficulty
     */
    public static String getDifficulty() {
        return difficulty;
    }

    /**
     * setDifficulty sets the difficulty
     * @param difficulty the difficulty
     */
    public static void setDifficulty(String difficulty) {
        Configurations.difficulty = difficulty;
    }

    /**
     * getMapType gets the map type
     * @return String the map type
     */
    public static String getMap_Type() {
        return map_Type;
    }

    /**
     * setMapType sets the map type
     * @param map_Type map type
     */
    public static void setMap_Type(String map_Type) {
        Configurations.map_Type = map_Type;
    }

    /**
     * getNum_Players gets the number of players
     * @return int the number of players
     */
    public static int getNum_Players() {
        return num_Players;
    }

    /**
     * setNum_Players sets the number of players
     * @param num_Players the number of players
     */
    public static void setNum_Players(int num_Players) {
        Configurations.num_Players = num_Players;
    }

    /**
     * getDisplayContents gets the displaycontents
     * @return the displayContents
     */
    public static DisplayContents getDisplayContents() {
        return displayContents;
    }

    /**
     * setter for DC
     * @param displayContents the DC
     */
    public static void setDisplayContents(DisplayContents displayContents) {
        Configurations.displayContents = displayContents;
    }

    /**
     *getting for curPlayer
     * @return Player curplayer
     */
    public static Player getCurPlayer() {
        return curPlayer;
    }

    /**
     * setting for cur Player
     * @param curPlayer the cur Player
     */
    public static void setCurPlayer(Player curPlayer) {
        Configurations.curPlayer = curPlayer;
    }

    /**
     * getter for round
     * @return int the round
     */
    public static int getRound() {
        return round;
    }

    /**
     * setter for round
     * @param round the round
     */
    public static void setRound(int round) {
        Configurations.round = round;
    }

    /**
     * getting for phase
     * @return int the phase
     */
    public static int getPhase() {
        return phase;
    }

    /**
     * setter for phase
     * @param phase the phase
     */
    public static void setPhase(int phase) {
        Configurations.phase = phase;
    }

    /**
     * getting for players
     * @return arrayList the players list
     */
    public static ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * setter for players
     * @param players the players to set
     */
    public static void setPlayers(ArrayList<Player> players) {
        Configurations.players = players;
    }
}
