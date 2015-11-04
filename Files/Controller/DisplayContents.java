package Controller;

import Model.*;

import java.util.ArrayList;

import Controller.LoopService;
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
    private PlayerSettings playerSettings;
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
    
    public DisplayContents(Stage primaryStage) {
        mainWindow = primaryStage;
    }

    public IntroScreen getIntroScreen() {
        return introScreen;
    }

    public void setIntroScreen(IntroScreen introScreen) {
        this.introScreen = introScreen;
    }

    public PlayerSettings getPlayerSettings() {
        return playerSettings;
    }

    public void setPlayerSettings(PlayerSettings playerSettings) {
        this.playerSettings = playerSettings;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public TownMap getTownMap() {
        return townMap;
    }

    public void setTownMap(TownMap townMap) {
        this.townMap = townMap;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Stage getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public Scene getIntroScreenGUI() {
        return introScreenGUI;
    }

    public void setIntroScreenGUI(Scene introScreenGUI) {
        this.introScreenGUI = introScreenGUI;
    }

    public ArrayList<Scene> getPlayerSettingsGUI() {
        return playerSettingsGUI;
    }

    public void setPlayerSettingsGUI(ArrayList<Scene> playerSettingsGUI) {
        this.playerSettingsGUI = playerSettingsGUI;
    }

    public Scene getGameScreenGUI() {
        return gameScreenGUI;
    }

    public void setGameScreenGUI(Scene gameScreenGUI) {
        this.gameScreenGUI = gameScreenGUI;
    }

    public Pane getMapGUI() {
        return mapGUI;
    }

    public void setMapGUI(Pane mapGUI) {
        this.mapGUI = mapGUI;
    }

    public Scene getTownMapGUI() {
        return townMapGUI;
    }

    public void setTownMapGUI(Scene townMapGUI) {
        this.townMapGUI = townMapGUI;
    }

    public Scene getStoreGUI() {
        return storeGUI;
    }

    public void setStoreGUI(Scene storeGUI) {
        this.storeGUI = storeGUI;
    }

    public LoopService getLoopService() {
        return loopService;
    }

    public void setLoopService(LoopService loopService) {
        this.loopService = loopService;
    }
    
    
    
}
