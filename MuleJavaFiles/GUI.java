import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 * Created by William on 9/10/2015.
 */
public class GUI extends Application{
    private Scene config1, config2, gameScreen;

    private Configurations config;

    private Scene[] players = new Scene[4];

    private GridPane config1_Grid, config2_Grid, config3_Grid;

    private Pane mapGui;

    private VBox config1_ButtonPanel;
    //, config2_ButtonPanel;

    private Button difficulty_Easy, difficulty_Medium, difficulty_Hard,
    map_River, map_Mountain, map_Plain,
    toConfig2;

    private Text difficulty_txt, mapType_txt, playerNum_txt;
    private ComboBox<String> player_number, race, color;

    private TextField name;

    private boolean movePhase = false;
    //---------------Map Data-----------------------------------------------------
    GameMap map = new GameMap();


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
        toNext.setOnAction(e -> {
            //------------ Creates Map / Game Screen -------------------------
            p.name = nameField.getText();
            if (cur == config.num_Players - 1) {
                createGameScreen(primaryStage);
            } else {
                primaryStage.setScene(players[cur + 1]);
                primaryStage.setTitle("Player " + (cur + 2));
            }
            //----------------------------------------------------------------
        });
        text.getChildren().addAll(name_Text, race_Text, color_Text);
        grid.add(text, 2, 2);
        grid.add(toNext, 2, 4);
        grid.setHgap(10);
        grid.setVgap(10);

        //grid.setGridLinesVisible(true);
    }

    public void createGameScreen(Stage primaryStage) {
        GridPane gameScreen_Layout = new GridPane();
        gameScreen_Layout.setHgap(20);
        gameScreen_Layout.setVgap(20);

        mapGui = map.generateMapGui();
        gameScreen = new Scene(gameScreen_Layout, 940, 540);
        gameScreen_Layout.add(mapGui, 1, 1);
        Chooser chooser = new Chooser();
        PlayerMove move;
        LoopService animate;
        chooser.start();
        //------------------Setting Up Arrow Key Movement-------------------------------
        move = new PlayerMove();
        animate = new LoopService(move, primaryStage);

        gameScreen.addEventHandler(KeyEvent.KEY_PRESSED, k -> {
            if (k.getCode() == KeyCode.LEFT) move.l = -5;
            if (k.getCode() == KeyCode.RIGHT) move.r = 5;
            if (k.getCode() == KeyCode.UP) move.u = -5;
            if (k.getCode() == KeyCode.DOWN) move.d = 5;
        });
        gameScreen.addEventHandler(KeyEvent.KEY_RELEASED, k -> {
            if (k.getCode() == KeyCode.LEFT) move.l = 0;
            if (k.getCode() == KeyCode.RIGHT) move.r = 0;
            if (k.getCode() == KeyCode.UP) move.u = 0;
            if (k.getCode() == KeyCode.DOWN) move.d = 0;
        });

        Text gameText = new Text(chooser.curPlayer.name + " Choose Initial Plot. Money: " + chooser.curPlayer.money);
        gameScreen.addEventHandler(KeyEvent.KEY_PRESSED, k -> {
            if (k.getCode() == KeyCode.SPACE && !movePhase) {
                chooser.attemptLandClaim();
                gameText.setText(chooser.curPlayer.name + " Choose Initial Plot. Money: " + chooser.curPlayer.money);
                if (chooser.allPassed()) {
                    gameText.setText(config.players.get(0).name + "'s Turn");
                    movePhase = true;
                }
            };
        });

        gameScreen.addEventHandler(KeyEvent.KEY_PRESSED, k -> {
            if (k.getCode() == KeyCode.P && !movePhase) {
                chooser.pass();
                if (chooser.allPassed()) {
                    movePhase = true;
                }
            };
        });

        //-------------TOWN-------------------------------------------
        Scene town = new Scene();
        
        gameScreen_Layout.add(gameText, 1, 0);
        primaryStage.setScene(gameScreen);
        primaryStage.setTitle("MULE");

        animate.start();

        //gameScreen_Layout.setGridLinesVisible(true);
    }

    public static void main (String[] args) {
        launch(args);
    }

    class Chooser{

        private int x;
        private int y;
        private MapTiles curTile;
        private Rectangle curRect;
        private Timeline t;
        protected Player curPlayer;
        protected int curPlayerNum;
        protected int loop = 0;
        protected boolean buyPhase = false;
        protected boolean[] passed;

        public Chooser() {
            passed = new boolean[config.num_Players];
            curTile = map.aMap[x][y];
            curRect = curTile.getMapTileGui();
            curPlayer = config.players.get(0);
            curPlayerNum = 0;
            x = 0;
            y = 0;
            curRect.setFill(Color.HOTPINK);
            t = new Timeline(new KeyFrame(
                    Duration.millis(1000),
                    ae -> {
                        if (!allPassed()) {
                            if (curTile.getOwner().equals("None")) curRect.setFill(curTile.getMapType());
                            incre();
                            while (!curTile.getOwner().equals("None") || curTile.getName().equals("Town")) {
                                incre();
                            }
                            curRect.setFill(Color.HOTPINK);
                        } else {
                            movePhase = true;
                        }
                    }));
            t.setCycleCount(Animation.INDEFINITE);
        }

        public void incre() {
            if (x == map.aMap.length - 1) {
                if (y == map.aMap[0].length - 1) {
                    x = 0;
                    y = 0;
                } else {
                    x = 0;
                    y++;
                }
            } else {
                x++;
            }
            curTile = map.aMap[x][y];
            curRect = curTile.getMapTileGui();
        }

        public void start() { t.play(); }
        public void pause() { t.pause(); }

        public void attemptLandClaim() {
            if (!buyPhase) {
                buyLand();
            } else {
                if (curPlayer.money > 300) {
                    buyLand();
                } else {
                    passed[curPlayerNum] = true;
                    increPlayer();
                }
            }
        }

        public void buyLand() {
            t.pause();
            if (buyPhase) curPlayer.money -= 300;
            if (curPlayer.owned.size() <= 0) {
                curPlayer.playerIcon = new Circle(x * MapTiles.getW() + 0.5 * MapTiles.getW()
                        , y * MapTiles.getH() + 0.5 * MapTiles.getH()
                        , 10);
                mapGui.getChildren().add(curPlayer.playerIcon);
            }
            curRect.setFill(curPlayer.color);
            curTile.setOwner(curPlayer.name);
            curPlayer.owned.add(curTile);
            if (curPlayer.money < 300) passed[curPlayerNum] = true;
            increPlayer();
            resetLand();
            t.play();
        }

        public void resetLand() {
            if (!allPassed()) {
                x = 0;
                y = 0;
                curTile = map.aMap[x][y];
                curRect = curTile.getMapTileGui();
                while (!curTile.getOwner().equals("None") || curTile.getName().equals("Town")) {
                    incre();
                }
                curRect.setFill(Color.HOTPINK);
            }
        }

        public boolean allPassed() {
            for (int i = 0; i < passed.length; i++) {
                if (!passed[i]) return false;
            }
            return true;
        }

        public void pass() {
            passed[curPlayerNum] = true;
            increPlayer();
        }

        public void increPlayer() {
            if (curPlayerNum == config.num_Players - 1) {
                if (loop == 1) {
                    buyPhase = true;
                } else {
                    loop++;
                }
                curPlayerNum = 0;
                curPlayer = config.players.get(0);
                resetLand();
            } else {
                curPlayerNum++;
                curPlayer = config.players.get(curPlayerNum);
            }
            if (passed[curPlayerNum]) {
                if (allPassed()) {
                    movePhase = true;
                    if (curTile.getOwner().equals("None")) curRect.setFill(curTile.getMapType());
                    t.stop();
                    return;
                }
                increPlayer();
            }
        }

    }

    class PlayerMove {
        protected int l = 0;
        protected int r = 0;
        protected int u = 0;
        protected int d = 0;
        protected char dir;
        protected Player curPlayer = config.players.get(0);
    }

    class LoopService extends AbstractLoopService {

        PlayerMove move;
        Circle playerIcon;
        double x;
        double y;
        int xSpeed;
        int ySpeed;

        public LoopService(PlayerMove move, Stage primaryStage) {
            this.move = move;
        }

        protected void runOnFXThread() {
            if (movePhase) {
                playerIcon = move.curPlayer.playerIcon;
                x = playerIcon.getCenterX();
                y = playerIcon.getCenterY();
                playerIcon.setCenterX(x + xSpeed);
                playerIcon.setCenterY(y + ySpeed);
                if (x + xSpeed < 500 && x + xSpeed > 400 && y + ySpeed < 300 && y + ySpeed > 200) {
                    
                }
            }
        }
        protected void runInBackground() {
            if (movePhase) {
                xSpeed = move.l + move.r;
                ySpeed = move.u + move.d;
                if (xSpeed == 5) move.dir = 'r';
                if (xSpeed == -5) move.dir = 'l';
                if (ySpeed == 5) move.dir = 'd';
                if (ySpeed == -5) move.dir = 'u';
            }
        }
    }
}
