import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
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
	private ComboBox player_number, race, color;

	private TextField name;

	@Override
	public void start(Stage primaryStage) throws Exception {
		config = new Configurations();
		config1_ButtonPanel = new VBox();
		config1_Grid = new GridPane();
		config1 = new Scene(config1_Grid, 1300, 700);
		//------------------------------------------------------------------------
		HBox difficultyBox = new HBox();
		difficulty_Easy = new Button("Easy");
		difficulty_Easy.setOnAction(e -> {
			config.difficulty = 0;
			difficulty_txt.setText("Difficulty : Easy");
		});
		difficulty_Medium = new Button("Medium");
		difficulty_Medium.setOnAction(e -> {
			config.difficulty = 1;
			difficulty_txt.setText("Difficulty : Medium");
		});
		difficulty_Hard = new Button("Hard");
		difficulty_Hard.setOnAction(e -> {
			config.difficulty = 2;
			difficulty_txt.setText("Difficulty : Hard");
		});
		difficultyBox.getChildren().addAll(difficulty_Easy, difficulty_Medium, difficulty_Hard);
		difficultyBox.setSpacing(10);
		//--------------------------------------------------------------------------
		HBox mapBox = new HBox();
		map_River = new Button("River");
		map_River.setOnAction(e -> {
			config.map_Type = 0;
			mapType_txt.setText("Map Type : River");
		});
		map_Mountain = new Button("Mountain");
		map_Mountain.setOnAction(e -> {
			config.map_Type = 1;
			mapType_txt.setText("Map Type : Mountain");
		});
		map_Plain = new Button("Plain");
		map_Plain.setOnAction(e -> {
			config.map_Type = 2;
			mapType_txt.setText("Map Type : Plain");
		});
		mapBox.getChildren().addAll(map_River, map_Mountain, map_Plain);
		mapBox.setSpacing(10);
		//--------------------------------------------------------------------------
		ObservableList<String> options =
				FXCollections.observableArrayList(
						"1",
						"2",
						"3",
						"4"
				);
		final ComboBox comboBox = new ComboBox(options);
		comboBox.setPromptText("Number of Players: ");
		comboBox.setEditable(true);
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
		});
		//-----------------------------------------------------------------------------
		VBox text = new VBox();
		text.setMinWidth(200);
		difficulty_txt = new Text("Difficulty : - ");
		mapType_txt = new Text("MapType : - ");
		playerNum_txt = new Text("Players : - ");
		text.getChildren().addAll(difficulty_txt, mapType_txt, playerNum_txt);
		//-------------------------------------------------------------------------------
		config1_Grid.add(difficultyBox, 1, 1);
		config1_Grid.add(mapBox, 1, 2);
		config1_Grid.add(comboBox, 1, 3);
		config1_Grid.add(text, 2, 2);
		config1_Grid.add(toConfig2, 3, 4);
		config1_Grid.setHgap(140);
		config1_Grid.setVgap(100);
		config1_Grid.setHalignment(difficultyBox, HPos.CENTER);
		config1_Grid.setHalignment(mapBox, HPos.CENTER);
		config1_Grid.setHalignment(comboBox, HPos.CENTER);

		//---------------------------------------------------------------------------
		//--------------------------------------------------------------------------
		primaryStage.setScene(config1);
		primaryStage.show();

		config1_Grid.setGridLinesVisible(true);

	}

	private void makeScene(Stage primaryStage, int cur) {
		Player p = new Player();
		config.players.add(p);
		GridPane grid = new GridPane();
		players[cur] = new Scene(grid, 500, 500);
		TextField nameField = new TextField("Enter your name: ");
		String tmpS = nameField.getText();
		nameField.setOnAction(event -> {
			p.name = tmpS;
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
		ComboBox race = new ComboBox(races);
		race.setPromptText("Choose a Race");
		race.setEditable(true);
		race.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				if (t1.equals("Elin")) p.race = 0;
				if (t1.equals("Blood Elves")) p.race = 1;
				if (t1.equals("Orc")) p.race = 2;
				if (t1.equals("High Humans")) p.race = 3;
				if (t1.equals("Protoss")) p.race = 4;
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
		ComboBox colorBox = new ComboBox(colors);
		race.setPromptText("Choose a Race");
		race.setEditable(true);
		race.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				if (t1.equals("Cool Cyan")) p.color = Color.DARKCYAN;
				if (t1.equals("Blazing Blue")) p.color = Color.ALICEBLUE;
				if (t1.equals("Popular Purple")) p.color = Color.MEDIUMPURPLE;
				if (t1.equals("Pretty Pink")) p.color = Color.HOTPINK;
				if (t1.equals("Yucky Yellow")) p.color = Color.LIGHTGOLDENRODYELLOW;
			}
		});
		grid.add(colorBox, 1, 3);
		Button toNext = new Button("Next");
		toNext.setOnAction(ee -> {
			if (cur == config.num_Players - 1) {
				primaryStage.setScene(gameScreen);
			} else {
				primaryStage.setScene(players[cur + 1]);
			}
		});
		grid.add(toNext, 2, 4);
	}

	public static void main (String[] args) {
		launch(args);
	}
}
