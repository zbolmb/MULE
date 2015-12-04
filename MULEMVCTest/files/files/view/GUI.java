/**
 * title: Project M.U.L.E.
 * authors: William Hsu, Min Je Jung, Zhijian Li, Karl Nicodemus, William Su
 * @version 1.3
 */
package files.view;

import files.model.Configurations;
import files.model.GameScreen;
import files.model.IntroScreen;
import files.controller.GuiController;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {

    /**
     * starts the MULE game
     * @param primaryStage primary windows
     * @throws Exception throws exceptions
     */
    public void start(Stage primaryStage) throws Exception {
        Configurations.initDC(primaryStage);
        
        GuiController controller = new GuiController();
        controller.init(primaryStage);
    }

    /**
     * main method
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
