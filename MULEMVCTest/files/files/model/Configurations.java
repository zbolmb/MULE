package files.model;

import files.controller.DisplayContents;
import files.controller.GameMapController;
import files.controller.GameScreenController;
import files.controller.GuiController;
import files.controller.IntroScreenController;
import files.controller.LoopService;
import files.controller.PlayerSettingsController;
import files.controller.StoreController;
import files.controller.TownMapController;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by William on 9/10/2015.
 */
public class Configurations {
    private static String difficulty = "Easy";
    private static String mapType = "River";
    private static int numberPlayers = 1;
    private static DisplayContents displayContents;
    private static Player curPlayer;
    private static GameMapController gameMapController;
    private static GameScreenController gameScreenController;
    private static IntroScreenController introScreenController;
    private static PlayerSettingsController playerSettingsController;
    private static StoreController storeController;
    private static TownMapController townMapController;
    private static LoopService loopService;
    private static GuiController guiController;

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

    public static void init(GameMapController gameMapControllert
            , GameScreenController gameScreenControllert
            , IntroScreenController introScreenControllert
            , PlayerSettingsController playerSettingsControllert
            , StoreController storeControllert
            , TownMapController townMapControllert
            , GuiController guiControllert) {
        gameMapController = gameMapControllert;
        gameScreenController = gameScreenControllert;
        introScreenController = introScreenControllert;
        playerSettingsController = playerSettingsControllert;
        storeController = storeControllert;
        townMapController = townMapControllert;
        guiController = guiControllert;
    }
    
    public static void initLS(LoopService ls) {
        loopService = ls;
    }
    
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
    public static String getMapType() {
        return mapType;
    }

    /**
     * setMapType sets the map type
     * @param mapType map type
     */
    public static void setMapType(String mapType) {
        Configurations.mapType = mapType;
    }

    /**
     * getNumPlayers gets the number of players
     * @return int the number of players
     */
    public static int getNumPlayers() {
        return numberPlayers;
    }

    /**
     * setNumPlayers sets the number of players
     * @param numPlayers the number of players
     */
    public static void setNumPlayers(int numPlayers) {
        Configurations.numberPlayers = numPlayers;
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
     * getter for phase
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

    public static GameMapController getGameMapController() {
        return gameMapController;
    }

    public void setGameMapController(GameMapController gameMapController) {
        this.gameMapController = gameMapController;
    }

    public static GameScreenController getGameScreenController() {
        return gameScreenController;
    }

    public void setGameScreenController(GameScreenController gameScreenController) {
        this.gameScreenController = gameScreenController;
    }

    public IntroScreenController getIntroScreenController() {
        return introScreenController;
    }

    public void setIntroScreenController(IntroScreenController introScreenController) {
        this.introScreenController = introScreenController;
    }

    public PlayerSettingsController getPlayerSettingsController() {
        return playerSettingsController;
    }

    public void setPlayerSettingsController(
            PlayerSettingsController playerSettingsController) {
        this.playerSettingsController = playerSettingsController;
    }

    public static StoreController getStoreController() {
        return storeController;
    }

    public void setStoreController(StoreController storeController) {
        this.storeController = storeController;
    }

    public static TownMapController getTownMapController() {
        return townMapController;
    }

    public void setTownMapController(TownMapController townMapController) {
        this.townMapController = townMapController;
    }
    
    public static GuiController getGuiController() {
        return guiController;
    }
    
    public static LoopService getLoopService() {
        return loopService;
    }
    
    
}
