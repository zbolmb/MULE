package files.controller;

import java.io.IOException;

import files.model.Configurations;
import files.view.IntroScreenView;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GuiController {
    
    IntroScreenController introScreenController;
    GameMapController gameMapController;
    GameScreenController gameScreenController;
    PlayerSettingsController playerSettingsController;
    StoreController storeController;
    TownMapController townMapController;
    Stage mainWindow;
    
    /**
     * similar to constructor, initialization method
     * need to setup certain controllers
     */
    public void init(Stage mainWindow) {
        this.mainWindow = mainWindow;
        introScreenController = new IntroScreenController(mainWindow);
        playerSettingsController = new PlayerSettingsController(mainWindow);
        gameMapController = new GameMapController();
        gameScreenController = new GameScreenController();
        townMapController = new TownMapController();
        storeController = new StoreController(mainWindow);
        introScreenController.setup(playerSettingsController);
        gameScreenController.setup(gameMapController, townMapController);
        storeController.setup(townMapController);
        playerSettingsController.setup(gameScreenController);
        
        introScreenController.updateView();
        Configurations.init(gameMapController
                , gameScreenController
                , introScreenController
                , playerSettingsController
                , storeController
                , townMapController
                , this);
        mainWindow.setScene(introScreenController.getView());
        mainWindow.setTitle("M.U.L.E.");
        mainWindow.setResizable(true);
        mainWindow.show();
    }
    
    public void setScene(Scene s) {
        mainWindow.setScene(s);
    }
}
