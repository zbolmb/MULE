package files.controller;

import java.io.IOException;

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
import javafx.stage.Stage;
import files.model.Configurations;
import files.model.Player;
import files.view.PlayerSettingsView;

public class PlayerSettingsController {

    private PlayerSettingsView playerSettingsView;
    private Stage mainWindow;
    private GameScreenController gameScreenController;

    /**
     * constructor for playerSettingsController
     */
    public PlayerSettingsController(Stage mainWindow) {
        playerSettingsView = new PlayerSettingsView();
        this.mainWindow = mainWindow;
    }

    /**
     * setup
     * @param gameScreenController gameScreen controller
     */
    public void setup(GameScreenController gameScreenController) {
        this.gameScreenController = gameScreenController;
    }

    /**
     * updates View
     */
    public void updateView() {
        Scene[] scenes = new Scene[Configurations.getNumPlayers()];
        for (int i = 0; i < Configurations.getNumPlayers(); i++) {
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
            Text nameText;
            TextField nameField;
            //Race
            Text raceText;
            ComboBox<String> race;
            ObservableList<String> races;
            //Color
            Text colorText;
            ComboBox<String> color;
            ObservableList<String> colors;
            //next
            Button next;

            nameText = new Text("Name : " + p.getName());
            raceText = new Text("Race : " + p.getRace());
            colorText = new Text("Color : Black");
            text.setMinWidth(100);
            text.setSpacing(10);
            nameField = new TextField("Enter your name : ");
            nameField.setOnAction(event -> {
                p.setName(nameField.getText());
                nameText.setText("Name : " + p.getName());
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
                    raceText.setText("Race : " + p.getRace());
                }
            });
            layout.add(race, 1, 2);
            //-----------------------------------------------------------------
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
                        colorText.setText("Color : Cool Cyan");
                    }
                    if (t1.equals("Blazing Blue")) {
                        p.setColor(Color.AQUA);
                        colorText.setText("Color : Blazing Blue");
                    }
                    if (t1.equals("Popular Purple")) {
                        p.setColor(Color.MEDIUMPURPLE);
                        colorText.setText("Color : Popular Purple");
                    }
                    if (t1.equals("Pretty Pink")) {
                        p.setColor(Color.HOTPINK);
                        colorText.setText("Color : Pretty Pink");
                    }
                    if (t1.equals("Yucky Yellow")) {
                        p.setColor(Color.LIGHTGOLDENRODYELLOW);
                        colorText.setText("Color : Yucky Yellow");
                    }
                }
            });
            layout.add(color, 1, 3);
            next = new Button("Next");
            int curPlayer = i;
            next.setOnAction(e -> {
                //------------ Creates Map / Game Screen ------------------
                if (!nameField.getText().equals("Enter your name : ")) {
                    p.setName(nameField.getText());
                }
                if (curPlayer == Configurations.getNumPlayers() - 1) {
                    Configurations.setCurPlayer(Configurations.getPlayers().get(0));
                    Configurations.getGameMapController().updateView();
                    LoopService ls = new LoopService();
                    Configurations.initLS(ls);
                    ls.init();
                    gameScreenController.updateView();
                    Configurations.getTownMapController().updateView();
                    try {
                        Configurations.getStoreController().updateView();
                    } catch (Exception e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                    try {
                        Util.incrementTurn();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    mainWindow.setScene(gameScreenController.getView());
                } else {
                    mainWindow.setScene(playerSettingsView
                            .get(curPlayer + 1));
                    mainWindow.setTitle("Player "
                            + (curPlayer + 1));
                }
                //---------------------------------------------------------
            });
            text.getChildren().addAll(nameText, raceText, colorText);
            text.setMaxWidth(100);
            layout.add(text, 2, 2);
            layout.add(next, 2, 4);
            layout.setHgap(20);
            layout.setVgap(20);
            layout.setPadding(new Insets(30, 0, 0, 35));
            scenes[i] = scene;
        }
        playerSettingsView.setScenes(scenes);
    }

    /**
     * adds text limiter
     * @param tf TextField
     * @param maxLength int
     */
    public static void addTextLimiter(final TextField tf,
            final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov,
                    final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

    /**
     * getter for View
     * @return PlayerSettingsView the view
     */
    public PlayerSettingsView getView() {
        return playerSettingsView;
    }
}