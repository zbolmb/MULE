package Model;

import Controller.DisplayContents;
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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

    public IntroScreen() {
        dc = Configurations.getDisplayContents();
        dc.setIntroScreen(this);
    }

    public Scene getGUI() {
        GridPane layout = new GridPane();
        Scene scene = new Scene(layout, 450, 300);

        /** 
         * Predefine objects
         */
        //Difficulty
        Button easy, medium, hard;
        Text difficulty_Text;
        //MapType
        Button river, mountain, plain;
        Text mapType_Text;
        //Player Number
        ComboBox<String> playerNum;
        Text playerNum_Text;
        //Next
        Button next;
        
        /**
         * Difficulty Settings
         */
        difficulty_Text = new Text("Difficulty : Easy");
        easy = new Button("Easy");
        easy.setOnAction(e -> {
            Configurations.setDifficulty("Easy");
            difficulty_Text.setText("Difficulty : " + Configurations.getDifficulty());
        });
        medium = new Button("Medium");
        medium.setOnAction(e -> {
            Configurations.setDifficulty("Medium");
            difficulty_Text.setText("Difficulty : " + Configurations.getDifficulty());
        });
        hard = new Button("Hard");
        hard.setOnAction(e -> {
            Configurations.setDifficulty("Hard");
            difficulty_Text.setText("Difficulty : " + Configurations.getDifficulty());
        });
        layout.add(easy, 1, 1);
        layout.add(medium, 2, 1);
        layout.add(hard, 3, 1);

        /**
         * Map Settings
         */
        mapType_Text = new Text("Map Type : River");
        river = new Button("River");
        river.setOnAction(e -> {
            Configurations.setMap_Type("River");
            mapType_Text.setText("Map Type : " + Configurations.getMap_Type());
        });
        mountain = new Button("Mountain");
        mountain.setOnAction(e -> {
            Configurations.setMap_Type("Mountain");
            mapType_Text.setText("Map Type : " + Configurations.getMap_Type());
        });
        plain = new Button("Plain");
        plain.setOnAction(e -> {
            Configurations.setMap_Type("Plain");
            mapType_Text.setText("Map Type : " + Configurations.getMap_Type());
        });
        layout.add(river, 1, 2);
        layout.add(mountain, 2, 2);
        layout.add(plain, 3, 2);

        /**
         * Player Number Settings
         */
        playerNum_Text = new Text("Number of Players : 1");
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
                Configurations.setNum_Players(Integer.parseInt(t1));
                playerNum_Text.setText("Players : " + t1);
            }
        });
        dc = Configurations.getDisplayContents();
        next = new Button("Next");
        next.setOnAction(e -> {
            dc.getPlayerSettings().updateDC();
            dc.getMainWindow().setScene((dc.getPlayerSettingsGUI()).get(0));
            dc.getMainWindow().setTitle("Player 1");
        });
        
        /**
         * Add To GridPane
         */
        VBox text = new VBox();
        text.getChildren().addAll(difficulty_Text, mapType_Text, playerNum_Text);
        layout.add(playerNum, 1, 3, 2, 1);
        layout.add(text, 4, 2);
        layout.add(next, 3, 5);
        layout.setHgap(20);
        layout.setVgap(20);
        layout.setPadding(new Insets(30, 0, 0, 35));
        GridPane.setHalignment(playerNum, HPos.CENTER);
        
        //layout.setGridLinesVisible(true);
        
        return scene;
    }
    
    public void updateDC() {
        dc.setIntroScreenGUI(getGUI());
        dc.setIntroScreen(this);
    }
}
