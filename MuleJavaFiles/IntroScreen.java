import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class IntroScreen {

    protected static DisplayContents dc;

    public IntroScreen() {
        dc = Configurations.displayContents;
        dc.introScreen = this;
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
        Text playerNum_Text;
        //Next
        Button next;
        
        /**
         * Difficulty Settings
         */
        difficulty_Text = new Text("Difficulty : Easy");
        easy = new Button("Easy");
        easy.setOnAction(e -> {
            Configurations.difficulty = "Easy";
            difficulty_Text.setText("Difficulty : " + Configurations.difficulty);
        });
        medium = new Button("Medium");
        medium.setOnAction(e -> {
            Configurations.difficulty = "Medium";
            difficulty_Text.setText("Difficulty : " + Configurations.difficulty);
        });
        hard = new Button("Hard");
        hard.setOnAction(e -> {
            Configurations.difficulty = "Hard";
            difficulty_Text.setText("Difficulty : " + Configurations.difficulty);
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
            Configurations.map_Type = "River";
            mapType_Text.setText("Map Type : " + Configurations.map_Type);
        });
        mountain = new Button("Mountain");
        mountain.setOnAction(e -> {
            Configurations.map_Type = "Mountain";
            mapType_Text.setText("Map Type : " + Configurations.map_Type);
        });
        plain = new Button("Plain");
        plain.setOnAction(e -> {
            Configurations.map_Type = "Plain";
            mapType_Text.setText("Map Type : " + Configurations.map_Type);
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
        final ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setPromptText("Number of Players : ");
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue ov, String t, String t1) {
                Configurations.num_Players = Integer.parseInt(t1);
                playerNum_Text.setText("Players : " + t1);
            }
        });
        dc = Configurations.displayContents;
        next = new Button("Next");
        next.setOnAction(e -> {
            dc.playerSettings.updateDC();
            dc.mainWindow.setScene((dc.playerSettingsGUI).get(0));
            dc.mainWindow.setTitle("Player 1");
        });
        
        /**
         * Add To GridPane
         */
        VBox text = new VBox();
        text.getChildren().addAll(difficulty_Text, mapType_Text, playerNum_Text);
        layout.add(comboBox, 1, 3, 2, 1);
        layout.add(text, 4, 2);
        layout.add(next, 4, 5);
        layout.setHgap(20);
        layout.setVgap(20);
        GridPane.setHalignment(comboBox, HPos.CENTER);
        
        return scene;
    }
    
    public void updateDC() {
        dc.introScreenGUI = getGUI();
        dc.introScreen = this;
    }
}
