import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class GameScreen {

    static DisplayContents dc;

    public GameScreen() {
        dc = Configurations.displayContents;
        dc.gameScreen = this;
    }

    public Scene getGUI() {
        GridPane layout = new GridPane();
        Scene scene = new Scene(layout, 940, 540);
        SelectionSquare sq;
        Text gameText;


        layout.add(dc.mapGUI, 1, 1);

        // add a white square around the plot that mouse is currently hovering over
        sq = new SelectionSquare();
        for (Rectangle r : sq.sq) dc.mapGUI.getChildren().add(r);

        /**
         * Temporary game text that displays on the top of the game screen
         * Tells whos turn it is to claim plot and displays money of the player
         */
        gameText = new Text(Configurations.curPlayer + " Choose Initial Plot. Money: " + Configurations.curPlayer.money);

        util.addMovementHandlers(scene);
        util.addMovementHandlers(dc.townMapGUI);

        //MOUSE_MOVED event handler. When mouse moves, moves the selection square to tile that mouse is hovering over
        dc.mapGUI.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            if (Configurations.phase == 0) {
                double x = e.getX(); //there is an offset of 20 for x
                double y = e.getY(); //there is an offset of 37 for y
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

    public void updateDC() {
        GameMap map = new GameMap();
        TownMap townMap = new TownMap();
        Store store = new Store();
        LoopService loopService = new LoopService();
        map.updateDC();
        townMap.updateDC();
        loopService.updateDC();
        store.updateDC();
        loopService.start();
        dc.gameScreenGUI = getGUI();
        dc.gameScreen = this;
    }
}
