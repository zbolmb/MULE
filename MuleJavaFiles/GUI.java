import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * @author Zhijian Li, William Hsu
 * The main class that has all the graphics
 * also has some behind the scene work (coded not so well)
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
    private boolean inTown = false;
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
        //------------------------Sets Up Configuration screen for each player--------
        toConfig2 = new Button("Next");
        toConfig2.setOnAction(e -> {
            Player p;
            for (int i = 0; i < config.num_Players; i++) {
                makeScene(primaryStage, i); // makes the separate configuration screens for each player
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
        config1_Grid.add(comboBox, 1, 3, 2, 1);
        config1_Grid.add(text, 4, 2);
        config1_Grid.add(toConfig2, 4, 5);
        config1_Grid.setHgap(20);
        config1_Grid.setVgap(20);
        GridPane.setHalignment(difficultyBox, HPos.CENTER);
        GridPane.setHalignment(mapBox, HPos.CENTER);
        GridPane.setHalignment(comboBox, HPos.CENTER);

        primaryStage.setScene(config1);
        primaryStage.show();
        primaryStage.setTitle("MULE");
        primaryStage.setResizable(false);
        //config1_Grid.setGridLinesVisible(true);

    }

    /**
     * makes the player configuration screen for each player
     * you can minimize this and ignore
     * @param primaryStage
     * @param cur
     */
    private void makeScene(Stage primaryStage, int cur) {
        Player p = new Player();
        VBox text = new VBox();
        Text name_Text = new Text("Name : " + p.name);
        Text race_Text = new Text("Race : " + p.race);
        Text color_Text = new Text("Color : Azure");
        text.setMinWidth(100);
        text.setSpacing(10);
        config.players.add(p);
        GridPane grid = new GridPane();
        players[cur] = new Scene(grid, 325, 200);
        TextField nameField = new TextField("Enter your name: ");
        nameField.setOnAction(event -> {
            p.name = nameField.getText();
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
    //----------------------------------------------------------------------------------------

    /**
     * This method creates the game screen (The actual game)
     * REQUIRES EDITING
     * RELEVANT FILES: 
     *      SelectionSquare: drawing tool for the selection icon with mouse
     *      TurnTracker: keeps track of players turn during buyPhase ONLY
     *      util: has some utility functions: CURRENTLY ONLY HAS TOOL FOR 
     *          DRAWING RECTANGLE TO MARK THAT TILE IS OWNED BY CURRENT PLAYER
     * Need to add turn based clock
     * @param primaryStage the stage that is being used (the window)
     */
    public void createGameScreen(Stage primaryStage) {
        GridPane gameScreen_Layout = new GridPane();
        gameScreen_Layout.setHgap(20);
        gameScreen_Layout.setVgap(20);

        mapGui = map.generateMapGui();
        gameScreen = new Scene(gameScreen_Layout, 940, 540);
        gameScreen_Layout.add(mapGui, 1, 1);
        PlayerMove move;
        LoopService animate;
        //------------------Setting Up Arrow Key Movement-------------------------------
        //IGNORE, helper class for animation
        move = new PlayerMove();

        // TEMP TOWN
        TownMap townMap = new TownMap();
        Pane townMapPane = townMap.generateMapGui();
        Scene town = new Scene(townMapPane, 800, 500);
        TownTurn townTurn = new TownTurn(config.players);

        /**
         * LoopService has data called curplayer
         * When implementing turn based, make sure to change curPlayer for different player. 
         */
        animate = new LoopService(move, primaryStage, town, townMapPane);

        /**
         * Handlers check for keypresses left, right, up, down arrow keys
         * If left is pressed, add value of-5 to left move (decrease X to move left)
         *    right is pressed, add 5 to right move (increase X to move up)
         *    up is pressed, add -5 to up (decrease Y to move up)
         *    down is pressed, add 5 to down (increase Y to move down)
         */
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
        town.addEventHandler(KeyEvent.KEY_PRESSED, k -> {
            if (k.getCode() == KeyCode.LEFT) move.l = -5;
            if (k.getCode() == KeyCode.RIGHT) move.r = 5;
            if (k.getCode() == KeyCode.UP) move.u = -5;
            if (k.getCode() == KeyCode.DOWN) move.d = 5;
        });
        town.addEventHandler(KeyEvent.KEY_RELEASED, k -> {
            if (k.getCode() == KeyCode.LEFT) move.l = 0;
            if (k.getCode() == KeyCode.RIGHT) move.r = 0;
            if (k.getCode() == KeyCode.UP) move.u = 0;
            if (k.getCode() == KeyCode.DOWN) move.d = 0;
        });

        //Add a turntracker class. Keeps track of the current player
        BuyPhaseTurnTracker turns = new BuyPhaseTurnTracker(config.players);
        // add a white square around the plot that mouse is currently hovering over
        SelectionSquare sq = new SelectionSquare();
        for (Rectangle r : sq.sq) mapGui.getChildren().add(r);

        /**
         * Temporary game text that displays on the top of the game screen
         * Tells whos turn it is to claim plot and displays money of the player
         */
        Text gameText = new Text(turns.getCurPlayer().name + " Choose Initial Plot. Money: " + turns.getCurPlayer().money);


        //MOUSE_MOVED event handler. When mouse moves, moves the selection square to tile that mouse is hovering over
        gameScreen.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            if (!movePhase) {
                double x = e.getX() - 20; //there is an offset of 20 for x
                double y = e.getY() - 37; //there is an offset of 37 for y
                sq.moveSelection(x, y);
            }
        });

        //MOUSE_PRESSED event handler. When mouse is pressed, the tile at that spot is claimed for the current player, if valid.
        //Does nothing if tile invalid
        gameScreen.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (!movePhase) {
                double x = e.getX() - 20;
                double y = e.getY() - 37;
                boolean done = claimTile(x, y, sq.getTile(x, y, map), turns, gameText);
                if (done) {
                    sq.remove();
                    movePhase = true;
                    //move.curPlayer = townTurn.getRichGuy();
                }
            }
        });

        /**
         * handler that checks for keypress "p"
         * when p is pressed, current player passes his / her turn
         */
        gameScreen.addEventHandler(KeyEvent.KEY_PRESSED, k -> {
            if (k.getCode() == KeyCode.P && !movePhase) {
                boolean done = turns.pass();
                if (done) {
                    sq.remove();
                    movePhase = true;
                    gameText.setText("Move Phase");
                    //move.curPlayer = townTurn.getRichGuy();
                }
            };
        });

        gameScreen_Layout.add(gameText, 1, 0);
        primaryStage.setScene(gameScreen);
        primaryStage.setTitle("MULE");

        animate.start();

        //gameScreen_Layout.setGridLinesVisible(true);
    }

    public static void main (String[] args) {
        launch(args);
    }

    /**
     * This method takes in the x, y (of mouse) and allows the current player to claim that land, if valid.
     * @param x coord of mouse
     * @param y coord of mouse
     * @param tile the tile in that location
     * @param turns the turntracker for buying phase
     * @param gameText the game text on top of screen
     * @return returns method nextTurn. Next turn returns
     *      false if players can still buy, true if everyone passed
     */
    public boolean claimTile(double x, double y, MapTiles tile, BuyPhaseTurnTracker turns, Text gameText) {
        if (!(tile instanceof townTile) && tile.getOwner().equals("None")) {
            Player curPlayer = turns.getCurPlayer();
            if (curPlayer.owned.size() <= 0) {
                int rx = (int)(x / 100) * 100;
                int ry = (int)(y / 100) * 100;
                curPlayer.playerIcon = new Circle(rx + 0.5 * MapTiles.getW()
                        , ry + 0.5 * MapTiles.getH()
                        , 10
                        , curPlayer.color);
                mapGui.getChildren().add(curPlayer.playerIcon);
            }
            curPlayer.owned.add(tile);
            tile.setOwner(curPlayer.name);
            if (turns.buyPhase) curPlayer.money -= 300;
            Rectangle[] sq = util.drawSelectionSq(x, y, curPlayer.color);

            for (Rectangle r : sq) mapGui.getChildren().add(r);
            boolean done = turns.nextTurn();
            if (done) {
                gameText.setText("Move Phase");
            } else {
                gameText.setText(turns.getCurPlayer().name + " Choose Initial Plot. Money: " + turns.getCurPlayer().money);
            }
            return done;
        }
        return false;
    }

    /**
     * Class that contains data for animation movement
     * @author Zhijian
     *
     */
    class PlayerMove {
        protected int l = 0;
        protected int r = 0;
        protected int u = 0;
        protected int d = 0;
        protected char dir;
        protected Player curPlayer = config.players.get(0);
    }

    /**
     * Animation Renderer that runs functions every certain frames
     * has a method to run on thread and to run off thread
     * @author Zhijian
     *
     */
    class LoopService extends AbstractLoopService {

        PlayerMove move;
        Stage primaryStage;
        Scene town;
        Circle playerIcon;
        Pane townMapPane;
        double x;
        double y;
        int xSpeed;
        int ySpeed;
        double time;

        public LoopService(PlayerMove move, Stage primaryStage, Scene town, Pane townMapPane) {
            this.move = move;
            this.primaryStage = primaryStage;
            this.town = town;
            this.townMapPane = townMapPane;
        }

        protected void runOnFXThread() {
            if (movePhase) {
                playerIcon = move.curPlayer.playerIcon;
                x = playerIcon.getCenterX();
                y = playerIcon.getCenterY();
                playerIcon.setCenterX(x + xSpeed);
                playerIcon.setCenterY(y + ySpeed);
                x = playerIcon.getCenterX();
                y = playerIcon.getCenterY();
                if (!inTown) {
                    if (x < 5) playerIcon.setCenterX(5);
                    if (x > 895) playerIcon.setCenterX(895);
                    if (y < 5) playerIcon.setCenterY(5);
                    if (y > 495) playerIcon.setCenterY(495);
                    if (x + xSpeed < 500 && x + xSpeed > 400 && y + ySpeed < 300 && y + ySpeed > 200) {
                        primaryStage.setScene(town);
                        townMapPane.getChildren().add(playerIcon);
                        mapGui.getChildren().remove(playerIcon);
                        playerIcon.setCenterX(400);
                        playerIcon.setCenterY(250);
                        inTown = true;
                        move.l = 0;
                        move.r = 0;
                        move.u = 0;
                        move.d = 0;
                    }
                } else {
                    if (x < 5 || x > 795 || y < 5 || y > 495) {
                        primaryStage.setScene(gameScreen);
                        townMapPane.getChildren().remove(playerIcon);
                        mapGui.getChildren().add(playerIcon);
                        inTown = false;
                        playerIcon.setCenterX(395);
                        playerIcon.setCenterY(250);
                        move.l = 0;
                        move.r = 0;
                        move.u = 0;
                        move.d = 0;
                    }
                    
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
            time = time + 0.02;
        }
    }
}
