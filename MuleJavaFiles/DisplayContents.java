import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


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
