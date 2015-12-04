package files.controller;
import java.util.ArrayList;
import files.model.Store;
import files.model.GameMap;
import files.model.GameScreen;
import files.model.IntroScreen;
import files.model.TownMap;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Contains all the graphics objects that will be used in GUI
 * this is constantly updated through updateDC in all
 * content classes.
 * This is so that every class can have access to
 * these contents through Configurations
 * @author Zhijian
 * @version 1.3
 */
public class DisplayContents {

    private IntroScreen introScreen;
    private GameScreen gameScreen;
    private GameMap map;
    private TownMap townMap;
    private Store store;

    private Stage mainWindow;
    private Scene introScreenGUI;
    private ArrayList<Scene> playerSettingsGUI;
    private Scene gameScreenGUI;
    private Pane mapGUI;
    private Scene townMapGUI;
    private Scene storeGUI;

    private LoopService loopService;

    /**
     * constructor of class
     * @param primaryStage Stage
     */
    public DisplayContents(Stage primaryStage) {
        mainWindow = primaryStage;
    }

    /**
     * getter for IntroScreen
     * @return IntroScreen the IntroScreen
     */
    public IntroScreen getIntroScreen() {
        return introScreen;
    }

    /**
     * setter for introScreen
     * @param introScreen the introScreen
     */
    public void setIntroScreen(IntroScreen introScreen) {
        this.introScreen = introScreen;
    }

    /**
     * getter for gameScreen
     * @return GameScreen the gameScreen
     */
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    /**
     * setter for gameScreen
     * @param gameScreen the gameScreen
     */
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    /**
     * getter for ma
     * @return GameMap the map
     */
    public GameMap getMap() {
        return map;
    }

    /**
     * setter for map
     * @param map the map
     */
    public void setMap(GameMap map) {
        this.map = map;
    }

    /**
     * getter for townMap
     * @return TownMap the TownMap
     */
    public TownMap getTownMap() {
        return townMap;
    }

    /**
     * setter for townMap
     * @param townMap the townMap
     */
    public void setTownMap(TownMap townMap) {
        this.townMap = townMap;
    }

    /**
     * getter for store
     * @return Store the store
     */
    public Store getStore() {
        return store;
    }

    /**
     * setter for store
     * @param store the store
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * getter for main Window
     * @return Stage the mainWindow
     */
    public Stage getMainWindow() {
        return mainWindow;
    }

    /**
     * setter for mainWindow
     * @param mainWindow the mainWindow
     */
    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * getter for introScreen
     * @return Scene the introScreen
     */
    public Scene getIntroScreenGUI() {
        return introScreenGUI;
    }

    /**
     * setter for introScreen
     * @param introScreenGUI the introScreen
     */
    public void setIntroScreenGUI(Scene introScreenGUI) {
        this.introScreenGUI = introScreenGUI;
    }

    /**
     * getter for PlayerSettingsGUI
     * @return ArrayList the PlayerSettingsGUI
     */
    public ArrayList<Scene> getPlayerSettingsGUI() {
        return playerSettingsGUI;
    }

    /**
     * setter for playerSettings GUI
     * @param playerSettingsGUI the playerSettingsGUI
     */
    public void setPlayerSettingsGUI(ArrayList<Scene> playerSettingsGUI) {
        this.playerSettingsGUI = playerSettingsGUI;
    }

    /**
     * getter for gameScreenGUI
     * @return Scene the gameScreenGUI
     */
    public Scene getGameScreenGUI() {
        return gameScreenGUI;
    }

    /**
     * setter for gameScreenGUI
     * @param gameScreenGUI the gameScreenGUI
     */
    public void setGameScreenGUI(Scene gameScreenGUI) {
        this.gameScreenGUI = gameScreenGUI;
    }

    /**
     * getter for mapGUI
     * @return Pane the mapGUI
     */
    public Pane getMapGUI() {
        return mapGUI;
    }

    /**
     * setter for mapGUI
     * @param mapGUI the mapGUI
     */
    public void setMapGUI(Pane mapGUI) {
        this.mapGUI = mapGUI;
    }

    /**
     * getter for townMap
     * @return Scene the townmapGUI
     */
    public Scene getTownMapGUI() {
        return townMapGUI;
    }

    /**
     * setter for townMap
     * @param townMapGUI the townMapGUI
     */
    public void setTownMapGUI(Scene townMapGUI) {
        this.townMapGUI = townMapGUI;
    }

    /**
     * getter for storeGUI
     * @return Scene the storeGUI
     */
    public Scene getStoreGUI() {
        return storeGUI;
    }

    /**
     * setter for storeGUI
     * @param storeGUI the storeGUI
     */
    public void setStoreGUI(Scene storeGUI) {
        this.storeGUI = storeGUI;
    }

    /**
     * getter for LoopService
     * @return LoopService the LoopService
     */
    public LoopService getLoopService() {
        return loopService;
    }

    /**
     * setter for loopservice
     * @param loopService the loopService
     */
    public void setLoopService(LoopService loopService) {
        this.loopService = loopService;
    }



}
