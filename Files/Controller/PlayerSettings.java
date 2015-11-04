package Controller;

import java.util.ArrayList;

import Controller.util;
import Model.Configurations;
import Model.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Content contains the scenes for selection screens
 * for the player
 * Players can modify their:
 * name
 * color
 * race
 * IMPORTANT*
 * updateDC() initializes all objects needed for playerSettings
 * @author Zhijian
 * @version 1.3
 *
 */
public class PlayerSettings {
    
    static DisplayContents dc;
    
    public PlayerSettings() {
        PlayerSettings.dc = Configurations.getDisplayContents();
        dc.setPlayerSettings(this);
    }

    public ArrayList<Scene> getGUI() {
        ArrayList<Scene> scenes = new ArrayList<>();
        for (int i = 0; i < Configurations.getNum_Players(); i++) {
            GridPane layout = new GridPane();
            Scene scene = new Scene(layout, 450, 300);
            
            //Create Player
            Player p = new Player();
            Configurations.getPlayers().add(p);
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
            
            name_Text = new Text("Name : " + p.getName());
            race_Text = new Text("Race : " + p.getRace());
            color_Text = new Text("Color : Black");
            text.setMinWidth(100);
            text.setSpacing(10);
            nameField = new TextField("Enter your name : ");
            nameField.setOnAction(event -> {
                p.setName(nameField.getText());
                name_Text.setText("Name : " + p.getName());
            });
            addTextLimiter(nameField, 12);
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
                        p.setRace("Elin");
                    }
                    if (t1.equals("Blood Elves")) {
                        p.setRace("Blood Elves");
                    }
                    if (t1.equals("Orc")) {
                        p.setRace("Orc");
                    }
                    if (t1.equals("High Humans")) {
                        p.setRace("High Humans");
                    }
                    if (t1.equals("Protoss")) {
                        p.setRace("Protoss");
                    }
                    race_Text.setText("Race : " + p.getRace());
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
                        p.setColor(Color.DARKCYAN);
                        color_Text.setText("Color : Cool Cyan");
                    }
                    if (t1.equals("Blazing Blue")) {
                        p.setColor(Color.AQUA);
                        color_Text.setText("Color : Blazing Blue");
                    }
                    if (t1.equals("Popular Purple")) {
                        p.setColor(Color.MEDIUMPURPLE);
                        color_Text.setText("Color : Popular Purple");
                    }
                    if (t1.equals("Pretty Pink")) {
                        p.setColor(Color.HOTPINK);
                        color_Text.setText("Color : Pretty Pink");
                    }
                    if (t1.equals("Yucky Yellow")) {
                        p.setColor(Color.LIGHTGOLDENRODYELLOW);
                        color_Text.setText("Color : Yucky Yellow");
                    }
                }
            });
            layout.add(color, 1, 3);
            next = new Button("Next");
            int curPlayer = i;
            next.setOnAction(e -> {
                //------------ Creates Map / Game Screen -------------------------
                if (curPlayer == Configurations.getNum_Players() - 1) {
                    util.incrementTurn();
                    dc.getGameScreen().updateDC();
                    dc.getMainWindow().setScene(dc.getGameScreenGUI());
                } else {
                    dc.getMainWindow().setScene(dc.getPlayerSettingsGUI().get(curPlayer + 1));
                    dc.getMainWindow().setTitle("Player " + (curPlayer + 1));
                }
                if (!nameField.getText().equals("Enter your name : ")) {
                    p.setName(nameField.getText());
                }
                //----------------------------------------------------------------
            });
            text.getChildren().addAll(name_Text, race_Text, color_Text);
            text.setMaxWidth(100);
            layout.add(text, 2, 2);
            layout.add(next, 2, 4);
            layout.setHgap(20);
            layout.setVgap(20);
            layout.setPadding(new Insets(30, 0, 0, 35));
            scenes.add(scene);
            
            //layout.setGridLinesVisible(true);
        }
        return scenes;
    }
    
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
    
    public void updateDC() {
        dc.setPlayerSettingsGUI(getGUI());
        dc.setPlayerSettings(this);
    }
}
