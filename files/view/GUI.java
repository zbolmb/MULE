/**
 * title: Project M.U.L.E.
 * authors: William Hsu, Min Je Jung, Zhijian Li, Karl Nicodemus, William Su
 * @version 1.3
 */
package files.view;

import files.model.Configurations;
import files.model.GameScreen;
import files.model.IntroScreen;
import files.controller.PlayerSettings;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;

public class GUI extends Application {

    /**
     * starts the MULE game
     * @param primaryStage primary windows
     * @throws Exception throws exceptions
     */
    public void start(Stage primaryStage) throws Exception {
        Configurations.initDC(primaryStage);
        IntroScreen intro = new IntroScreen();
        PlayerSettings playerSettings = new PlayerSettings();
        GameScreen gameScreen = new GameScreen();
        intro.updateDC();

        final URL resource = getClass().getResource("/files/model/music.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

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

        primaryStage.setScene(Configurations
                .getDisplayContents().getIntroScreenGUI());
        primaryStage.show();
        primaryStage.setTitle("M.U.L.E.");
        primaryStage.setResizable(true);

    }

    /**
     * main method
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
