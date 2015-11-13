package model;

import controller.DisplayContents;
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
import java.io.IOException;



/**
 * The intro screen in the beginning of the game
 * contains settings for:
 * difficulty ("Easy", "Medium", "Hard")
 * map type ("River", "Mountain", "Plain")
 * number of players (1, 2, 3, 4)
 * IMPORTANT *
 * updateDC() initializes all objects needed for introscreen
 * @author Zhijian
 * @version 1.3
 */
public class IntroScreen {

    protected static DisplayContents dc;

    /**
     * IntroScreen method
     */
    public IntroScreen() {
        dc = Configurations.getDisplayContents();
        dc.setIntroScreen(this);
    }

    /**
     * Scene method
     * @return data
     */
    public Scene getGUI() {
        GridPane layout = new GridPane();
        Scene scene = new Scene(layout, 450, 300);

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
        layout.add(easy, 1, 1);
        layout.add(medium, 2, 1);
        layout.add(hard, 3, 1);

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
        layout.add(river, 1, 2);
        layout.add(mountain, 2, 2);
        layout.add(plain, 3, 2);

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
        dc = Configurations.getDisplayContents();
        next = new Button("Next");
        next.setOnAction(e -> {
                dc.getPlayerSettings().updateDC();
                dc.getMainWindow().setScene((dc.getPlayerSettingsGUI()).get(0));
                dc.getMainWindow().setTitle("Player 1");
            });

        load = new Button("Load");
        load.setOnAction(e -> {
                try {
                    controller.Load.load(dc.getMainWindow());
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
        layout.add(playerNum, 1, 3, 2, 1);
        layout.add(text, 4, 2);
        layout.add(load, 3, 5);
        layout.add(next, 2, 5);
        layout.setHgap(20);
        layout.setVgap(20);
        layout.setPadding(new Insets(30, 0, 0, 35));
        GridPane.setHalignment(playerNum, HPos.CENTER);
        //layout.setGridLinesVisible(true);
        return scene;
    }

    /**
     * updateDC method
     */
    public void updateDC() {
        dc.setIntroScreenGUI(getGUI());
        dc.setIntroScreen(this);
    }
}
