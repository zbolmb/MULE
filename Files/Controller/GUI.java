/**
 * title: Project M.U.L.E.
 * authors: William Hsu, Min Je Jung, Zhijian Li, Karl Nicodemus, William Su
 * @version 1.3
 */
package Controller;

import Model.Configurations;
import Model.GameScreen;
import Model.IntroScreen;
import Model.PlayerSettings;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application{

    public void start(Stage primaryStage) throws Exception {
        Configurations.initDC(primaryStage);
        IntroScreen intro = new IntroScreen();
        PlayerSettings playerSettings = new PlayerSettings();
        GameScreen gameScreen = new GameScreen();
        intro.updateDC();
        
        /**
         * WHEN CREATING NEW CONTENT, FOLLOW THESE STEPS
         * each piece of content (store, town) contain its own class
         * each of these classes has a getGUI() and updateDC()
         * updateDC calls getGUI and basically updates
         * DisplayContent, which contains all the graphics pieces
         * of the GUI
         * The next button in intro calls updateDC for playersettings, the 
         * player selection screens
         */
        

        primaryStage.setScene(Configurations.getDisplayContents().getIntroScreenGUI());
        primaryStage.show();
        primaryStage.setTitle("M.U.L.E.");
        primaryStage.setResizable(true);

    }

    public static void main (String[] args) {
        launch(args);
    }

}
