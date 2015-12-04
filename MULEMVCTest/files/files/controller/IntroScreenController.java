package files.controller;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import files.model.Configurations;
import files.model.IntroScreen;
import files.view.IntroScreenView;
import files.view.PlayerSettingsView;

public class IntroScreenController {

    private IntroScreen screen;
    private IntroScreenView screenView;
    private Stage mainWindow;
    private PlayerSettingsController playerSettingsController;
    
    /**
     * constructor for IntroScreenController
     */
    public IntroScreenController(Stage mainWindow) {
        screen = new IntroScreen();
        screenView = new IntroScreenView(new GridPane());
        this.mainWindow = mainWindow;
    }
    
    /**
     * set PlayerSettingsView
     * @param settings playerSettingsView
     */
    public void setup(PlayerSettingsController playerSettingsController) {
        this.playerSettingsController = playerSettingsController;
    }
    
    /**
     * updatesView
     * @return data
     */
    public void updateView() {
        /**
         * Predefine objects
         */
        //Difficulty
        Button easy;
        Button medium;
        Button hard;
        Text difficultyText;
        //MapType
        Button river;
        Button mountain;
        Button plain;
        Text mapTypeText;
        //Player Number
        ComboBox<String> playerNum;
        Text playerNumText;
        //Next
        Button next;
        Button load;
        /**
         * Difficulty Settings
         */
        difficultyText = new Text("Difficulty : Easy");
        easy = new Button("Easy");
        easy.setOnAction(e -> {
                Configurations.setDifficulty("Easy");
                difficultyText.setText("Difficulty : "
                        + Configurations.getDifficulty());
            });
        medium = new Button("Medium");
        medium.setOnAction(e -> {
                Configurations.setDifficulty("Medium");
                difficultyText.setText("Difficulty : "
                        + Configurations.getDifficulty());
            });
        hard = new Button("Hard");
        hard.setOnAction(e -> {
                Configurations.setDifficulty("Hard");
                difficultyText.setText("Difficulty : "
                        + Configurations.getDifficulty());
            });
        screenView.add(easy, 1, 1);
        screenView.add(medium, 2, 1);
        screenView.add(hard, 3, 1);

        /**
         * Map Settings
         */
        mapTypeText = new Text("Map Type : River");
        river = new Button("River");
        river.setOnAction(e -> {
                Configurations.setMapType("River");
                mapTypeText.setText("Map Type : "
                        + Configurations.getMapType());
            });
        mountain = new Button("Mountain");
        mountain.setOnAction(e -> {
                Configurations.setMapType("Mountain");
                mapTypeText.setText("Map Type : "
                        + Configurations.getMapType());
            });
        plain = new Button("Plain");
        plain.setOnAction(e -> {
                Configurations.setMapType("Plain");
                mapTypeText.setText("Map Type : "
                        + Configurations.getMapType());
            });
        screenView.add(river, 1, 2);
        screenView.add(mountain, 2, 2);
        screenView.add(plain, 3, 2);

        /**
         * Player Number Settings
         */
        playerNumText = new Text("Number of Players : 1");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "1",
                        "2",
                        "3",
                        "4"
                        );
        playerNum = new ComboBox<>(options);
        playerNum.setPromptText("Number of Players : ");
        playerNum.valueProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue ov, String t, String t1) {
                Configurations.setNumPlayers(Integer.parseInt(t1));
                playerNumText.setText("Players : " + t1);
            }
        });
        next = new Button("Next");
        next.setOnAction(e -> {
                playerSettingsController.updateView();
                mainWindow.setScene(playerSettingsController.getView().get(0));
                mainWindow.setTitle("Player 1");
            });

        load = new Button("Load");
        load.setOnAction(e -> {
                try {
                    files.controller.Load.load(mainWindow);
                } catch (IOException x) {
                    x.printStackTrace();
                }
            });
        /**
         * Add To GridPane
         */
        VBox text = new VBox();
        text.getChildren().addAll(difficultyText,
                mapTypeText, playerNumText);
        screenView.getLayout().add(playerNum, 1, 3, 2, 1);
        screenView.add(text, 4, 2);
        screenView.add(load, 3, 5);
        screenView.add(next, 2, 5);
        screenView.getLayout().setHgap(20);
        screenView.getLayout().setVgap(20);
        screenView.getLayout().setPadding(new Insets(30, 0, 0, 35));
        GridPane.setHalignment(playerNum, HPos.CENTER);
    }
    
    /**
     * getter for view
     * @return IntroScreenView
     */
    public IntroScreenView getView() {
        return screenView;
    }
}
