import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Content contains the game screen where main game takes place
 * gameText is the text that flashes on top of the map
 * contains mouse handlers for initial plot selection
 * contains keypress handler for if player skips (pressing p)
 * IF ANYTHING IS REQUIRED FOR GAMESCREEN, INITIALIZE THAT
 * CONTENT UNDER updateDC()
 * @author Zhijian
 *
 */
public class GameScreen {

    static DisplayContents dc;
    Text gameText;

    public GameScreen() {
        dc = Configurations.displayContents;
        dc.gameScreen = this;
        gameText = new Text();
    }

    public Scene getGUI() {
        GridPane layout = new GridPane();
        Scene scene = new Scene(layout, 940, 610);
        SelectionSquare sq;


        layout.add(dc.mapGUI, 1, 1);

        // add a white square around the plot that mouse is currently hovering over
        sq = new SelectionSquare();
        for (Rectangle r : sq.sq) dc.mapGUI.getChildren().add(r);

        updateText();
        util.addMovementHandlers(scene);
        util.addMovementHandlers(dc.townMapGUI);

        //MOUSE_MOVED event handler. When mouse moves, moves the selection square to tile that mouse is hovering over
        dc.mapGUI.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            if (Configurations.phase == 0) {
                double x = e.getX(); 
                double y = e.getY();
                sq.moveSelection(x, y);
            }
        });

        // MOUSE_PRESSED event handler. When mouse is pressed, the tile at that spot
        // is claimed for the current player, if valid.
        // Does nothing if tile invalid
        dc.mapGUI.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (Configurations.phase == 0) {
                double x = e.getX();
                double y = e.getY();
                util.claimTile(x, y, sq.getTile(x, y, dc.map));
                if (Configurations.round > 0) {
                    sq.remove();
                    Configurations.phase = 1;
                }
            }
        });

        /**
         * handler that checks for keypress "p"
         * when p is pressed, current player passes his / her turn
         */
        scene.addEventHandler(KeyEvent.KEY_PRESSED, p -> {
            if (Configurations.phase == 0 && p.getCode() == KeyCode.P) {
                Configurations.curPlayer.passed = true;
                util.incrementTurn();
                if (Configurations.round > 0) {
                    sq.remove();
                    Configurations.phase = 1;
                }
            }
        });

        layout.setHgap(20);
        layout.setVgap(20);

        layout.add(gameText, 1, 0);

        return scene;
    }

    /**
     * update DC creates everything that gamescreen requires:
     * GameMap, TownMap, Store, LoopService
     */
    public void updateDC() {
        GameMap map = new GameMap();
        TownMap townMap = new TownMap();
        Store store = new Store();
        map.updateDC();
        LoopService loopService = new LoopService();
        townMap.updateDC();
        loopService.updateDC();
        store.updateDC();
        loopService.start();
        dc.gameScreenGUI = getGUI();
        dc.gameScreen = this;
    }

    /**
     * updates the gameText to present values
     */
    public void updateText() {
        Player cp = Configurations.curPlayer;
        gameText.setText(cp.name + "'s Turn. "
                + "\nMoney : $" + cp.money
                + " | Food : " + cp.food
                + " | Energy : " + cp.energy
                + " | Smithore : " + cp.smithore
                + "\nFood Mule : " + cp.mule1
                + " | Energy Mule : " + cp.mule2
                + " | Ore Mule : " + cp.mule3
                + "\nScore : " + cp.score);
        if (dc.loopService != null) {
            gameText.setText(gameText.getText() + "\nTime : " + (120 - (int)dc.loopService.time));
        }
        
    }
}
