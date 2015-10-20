/**
 * title: Project M.U.L.E.
 * authors: William Hsu, Min Je Jung, Zhijian Li, Karl Nicodemus, William Su
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application{

    private Configurations config;


    @Override
    public void start(Stage primaryStage) throws Exception {
        config = new Configurations(primaryStage);
        IntroScreen intro = new IntroScreen();
        PlayerSettings playerSettings = new PlayerSettings();
        GameScreen gameScreen = new GameScreen();
        intro.updateDC();
        
        /**
         * Must create everything first
         * IntroScreen, PlayerSettings, GameScreen
         * Use UpdateDC to create the GUI of that thing
         * eg. intro.updateDC()
         * Updates the DisplayContents that contains the scene corresponding to intro
         */
        //create Intro first 
        //on button press in intro screen, updatesDC for player settings
        //on button press in player settings, updatesDC for the map, townmap and gamescreen
        

        primaryStage.setScene(Configurations.displayContents.introScreenGUI);
        primaryStage.show();
        primaryStage.setTitle("M.U.L.E.");
        primaryStage.setResizable(true);
        //config1_Grid.setGridLinesVisible(true);

    }

    public static void main (String[] args) {
        launch(args);
    }

}
