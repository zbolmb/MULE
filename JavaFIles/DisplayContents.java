import java.util.ArrayList;

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
    
    protected IntroScreen introScreen;
    protected PlayerSettings playerSettings;
    protected GameScreen gameScreen;
    protected GameMap map;
    protected TownMap townMap;
    protected Store store;

    protected Stage mainWindow;
    protected Scene introScreenGUI;
    protected ArrayList<Scene> playerSettingsGUI;
    protected Scene gameScreenGUI;
    protected Pane mapGUI;
    protected Scene townMapGUI;
    protected Scene storeGUI;
    
    protected LoopService loopService;
    
    public DisplayContents(Stage primaryStage) {
        mainWindow = primaryStage;
    }
}
