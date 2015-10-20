import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class PlayerSettings {
    
    static DisplayContents dc;
    
    public PlayerSettings() {
        PlayerSettings.dc = Configurations.displayContents;
        dc.playerSettings = this;
    }

    public ArrayList<Scene> getGUI() {
        ArrayList<Scene> scenes = new ArrayList<>();
        for (int i = 0; i < Configurations.num_Players; i++) {
            GridPane layout = new GridPane();
            Scene scene = new Scene(layout, 450, 300);
            
            //Create Player
            Player p = new Player();
            Configurations.players.add(p);
            /**
             * Predefine Objects
             */
            VBox text = new VBox();
            //Name
            Text name_Text;
            TextField nameField;
            //Race
            Text race_Text;
            ComboBox<String> race;
            ObservableList<String> races;
            //Color
            Text color_Text;
            ComboBox<String> color;
            ObservableList<String> colors;
            //next
            Button next;
            
            name_Text = new Text("Name : " + p.name);
            race_Text = new Text("Race : " + p.race);
            color_Text = new Text("Color : Azure");
            text.setMinWidth(100);
            text.setSpacing(10);
            nameField = new TextField("Enter your name : ");
            nameField.setOnAction(event -> {
                p.name = nameField.getText();
                name_Text.setText("Name : " + p.name);
            });
            layout.add(nameField, 1, 1);
            races = FXCollections.observableArrayList(
                            "Elin",
                            "Blood Elves",
                            "Orc",
                            "High Humans",
                            "Protoss"
                            );
            race = new ComboBox<>(races);
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
            layout.add(race, 1, 2);
            //-----------------------------------------------------------------------
            colors = FXCollections.observableArrayList(
                            "Cool Cyan",
                            "Blazing Blue",
                            "Popular Purple",
                            "Pretty Pink",
                            "Yucky Yellow"
                            );
             color = new ComboBox<>(colors);
            color.setPromptText("Choose a Color");
            //colorBox.setEditable(true);
            color.valueProperty().addListener(new ChangeListener<String>() {
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
            layout.add(color, 1, 3);
            next = new Button("Next");
            int curPlayer = i;
            next.setOnAction(e -> {
                //------------ Creates Map / Game Screen -------------------------
                if (curPlayer == Configurations.num_Players - 1) {
                    util.incrementTurn();
                    dc.gameScreen.updateDC();
                    dc.mainWindow.setScene(dc.gameScreenGUI);
                } else {
                    dc.mainWindow.setScene(dc.playerSettingsGUI.get(curPlayer + 1));
                    dc.mainWindow.setTitle("Player " + (curPlayer + 1));
                }
                if (!nameField.getText().equals("Enter your name : ")) {
                    p.name = nameField.getText();
                }
                //----------------------------------------------------------------
            });
            text.getChildren().addAll(name_Text, race_Text, color_Text);
            layout.add(text, 2, 2);
            layout.add(next, 2, 4);
            layout.setHgap(10);
            layout.setVgap(10);
            scenes.add(scene);
        }
        return scenes;
    }
    
    public void updateDC() {
        dc.playerSettingsGUI = getGUI();
        dc.playerSettings = this;
    }
}
