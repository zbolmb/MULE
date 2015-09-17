import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.peer.ButtonPeer;

/**
 * Created by William on 9/10/2015.
 */
public class GUI extends Application{
    private Scene config1, config2, gameScreen;

    private Configurations config;

    private Scene[] players = new Scene[4];

    private GridPane config1_Grid, config2_Grid, config3_Grid;

    private VBox config1_ButtonPanel;
    //, config2_ButtonPanel;

    private Button difficulty_Easy, difficulty_Medium, difficulty_Hard,
    map_River, map_Mountain, map_Plain,
    toConfig2;

    private Text difficulty_txt, mapType_txt, playerNum_txt;
    private ComboBox<String> player_number, race, color;

    private TextField name;

    @Override
    public void start(Stage primaryStage) throws Exception {	    
        config = new Configurations();
        config1_ButtonPanel = new VBox();
        config1_Grid = new GridPane();
        config1 = new Scene(config1_Grid, 400, 225);
        //------------------------------------------------------------------------
        HBox difficultyBox = new HBox();
        difficulty_Easy = new Button("Easy");
        difficulty_Easy.setOnAction(e -> {
            config.difficulty = "Easy";
            difficulty_txt.setText("Difficulty : " + config.difficulty);
        });
        difficulty_Medium = new Button("Medium");
        difficulty_Medium.setOnAction(e -> {
            config.difficulty = "Medium";
            difficulty_txt.setText("Difficulty : " + config.difficulty);
        });
        difficulty_Hard = new Button("Hard");
        difficulty_Hard.setOnAction(e -> {
            config.difficulty = "Hard";
            difficulty_txt.setText("Difficulty : " + config.difficulty);
        });
        //difficultyBox.getChildren().addAll(difficulty_Easy, difficulty_Medium, difficulty_Hard);
        //difficultyBox.setSpacing(10);
        config1_Grid.add(difficulty_Easy, 1, 1);
        config1_Grid.add(difficulty_Medium, 2, 1);
        config1_Grid.add(difficulty_Hard, 3, 1);
        //--------------------------------------------------------------------------
        HBox mapBox = new HBox();
        map_River = new Button("River");
        map_River.setOnAction(e -> {
            config.map_Type = "River";
            mapType_txt.setText("Map Type : " + config.map_Type);
        });
        map_Mountain = new Button("Mountain");
        map_Mountain.setOnAction(e -> {
            config.map_Type = "Mountain";
            mapType_txt.setText("Map Type : " + config.map_Type);
        });
        map_Plain = new Button("Plain");
        map_Plain.setOnAction(e -> {
            config.map_Type = "Plain";
            mapType_txt.setText("Map Type : " + config.map_Type);
        });
        //mapBox.getChildren().addAll(map_River, map_Mountain, map_Plain);
        //mapBox.setSpacing(10);
        config1_Grid.add(map_River, 1, 2);
        config1_Grid.add(map_Mountain, 2, 2);
        config1_Grid.add(map_Plain, 3, 2);
        //--------------------------------------------------------------------------
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "1",
                        "2",
                        "3",
                        "4"
                        );
        final ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setPromptText("Number of Players: ");
        //comboBox.setEditable(true);
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                config.num_Players = Integer.parseInt(t1);
                playerNum_txt.setText("Players : " + t1);
            }
        });
        //----------------------------------------------------------------------------
        toConfig2 = new Button("Next");
        toConfig2.setOnAction(e -> {
            Player p;
            for (int i = 0; i < config.num_Players; i++) {
                makeScene(primaryStage, i);
            }
            primaryStage.setScene(players[0]);
            primaryStage.setTitle("Player 1");
        });
        //-----------------------------------------------------------------------------
        VBox text = new VBox();
        text.setMinWidth(150);
        difficulty_txt = new Text("Difficulty : Easy ");
        mapType_txt = new Text("MapType : River ");
        playerNum_txt = new Text("Players : 1 ");
        text.getChildren().addAll(difficulty_txt, mapType_txt, playerNum_txt);
        //-------------------------------------------------------------------------------
        //config1_Grid.add(difficultyBox, 1, 1);
        //config1_Grid.add(mapBox, 1, 2);
        config1_Grid.add(comboBox, 1, 3, 2, 1);
        config1_Grid.add(text, 4, 2);
        config1_Grid.add(toConfig2, 4, 5);
        config1_Grid.setHgap(20);
        config1_Grid.setVgap(20);
        GridPane.setHalignment(difficultyBox, HPos.CENTER);
        GridPane.setHalignment(mapBox, HPos.CENTER);
        GridPane.setHalignment(comboBox, HPos.CENTER);

        //---------------------------------------------------------------------------
        //--------------------------------------------------------------------------
        primaryStage.setScene(config1);
        primaryStage.show();
        primaryStage.setTitle("MULE");
        primaryStage.setResizable(false);
        //config1_Grid.setGridLinesVisible(true);

    }

    private void makeScene(Stage primaryStage, int cur) {
        Player p = new Player();
        VBox text = new VBox();
        Text name_Text = new Text("Name :");
        Text race_Text = new Text("Race :");
        Text color_Text = new Text("Color : ");
        text.setMinWidth(100);
        text.setSpacing(10);
        //text.setPadding(new Insets(20, 0, 0, 0));
        config.players.add(p);
        GridPane grid = new GridPane();
        players[cur] = new Scene(grid, 325, 200);
        TextField nameField = new TextField("Enter your name: ");
        nameField.setOnAction(event -> {
            p.name = nameField.getText();;
            name_Text.setText("Name : " + p.name);
        });
        grid.add(nameField, 1, 1);
        ObservableList<String> races =
                FXCollections.observableArrayList(
                        "Elin",
                        "Blood Elves",
                        "Orc",
                        "High Humans",
                        "Protoss"
                        );
        ComboBox<String> race = new ComboBox<>(races);
        race.setPromptText("Choose a Race");
        //race.setEditable(true);
        race.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (t1.equals("Elin")) {
                    p.race = "Elin";
                }
                if (t1.equals("Blood Elves")) {
                    p.race = "Blood Elves";
                }
                if (t1.equals("Orc")) {
                    p.race = "Orc";
                }
                if (t1.equals("High Humans")) {
                    p.race = "High Humans";
                }
                if (t1.equals("Protoss")) {
                    p.race = "Protoss";
                }
                race_Text.setText("Race : " + p.race);
            }
        });
        grid.add(race, 1, 2);
        //-----------------------------------------------------------------------
        ObservableList<String> colors =
                FXCollections.observableArrayList(
                        "Cool Cyan",
                        "Blazing Blue",
                        "Popular Purple",
                        "Pretty Pink",
                        "Yucky Yellow"
                        );
        ComboBox<String> colorBox = new ComboBox<>(colors);
        colorBox.setPromptText("Choose a Color");
        //colorBox.setEditable(true);
        colorBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (t1.equals("Cool Cyan")) {
                    p.color = Color.DARKCYAN;
                    color_Text.setText("Color : Cool Cyan");
                }
                if (t1.equals("Blazing Blue")) {
                    p.color = Color.ALICEBLUE;
                    color_Text.setText("Color : Blazing Blue");
                }
                if (t1.equals("Popular Purple")) {
                    p.color = Color.MEDIUMPURPLE;
                    color_Text.setText("Color : Popular Purple");
                }
                if (t1.equals("Pretty Pink")) {
                    p.color = Color.HOTPINK;
                    color_Text.setText("Color : Pretty Pink");
                }
                if (t1.equals("Yucky Yellow")) {
                    p.color = Color.LIGHTGOLDENRODYELLOW;
                    color_Text.setText("Color : Yucky Yellow");
                }
            }
        });
        grid.add(colorBox, 1, 3);
        Button toNext = new Button("Next");
        toNext.setOnAction(ee -> {
            p.name = nameField.getText();
            if (cur == config.num_Players - 1) {				
                /** Temp gameScreen **/
                GridPane tmpPane = new GridPane();
                gameScreen = new Scene(tmpPane, 500, 500);
                VBox tmpBox = new VBox();
                tmpBox.getChildren().addAll(
                        new Text("Difficulty : " + config.difficulty)
                        , new Text("Map Type : " + config.map_Type)
                        , new Text("Number Of Players : " + config.num_Players)
                        );
                for (int i = 0; i < config.num_Players; i++) {
                    tmpBox.getChildren().addAll(
                            new Text("Player " + (i + 1))
                            , new Text("Name : " + config.players.get(i).name)
                            , new Text("Race : " + config.players.get(i).race)
                            , new Text("Color : " + config.players.get(i).color)
                            );
                }
                tmpPane.add(tmpBox, 1, 1);

                /** Temp gameScreen **/
                primaryStage.setScene(gameScreen);
                primaryStage.setTitle("MULE");		        
            } else {
                primaryStage.setScene(players[cur + 1]);
                primaryStage.setTitle("Player " + (cur + 2));
            }
        });
        text.getChildren().addAll(name_Text, race_Text, color_Text);
        grid.add(text, 2, 2);
        grid.add(toNext, 2, 4);
        grid.setHgap(10);
        grid.setVgap(10);

        //grid.setGridLinesVisible(true);
    }

    public static void main (String[] args) {
        launch(args);
    }
}