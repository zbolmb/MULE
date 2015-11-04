package Model;

import Controller.DisplayContents;
import Controller.LoopService;
import Controller.util;
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
        dc = Configurations.getDisplayContents();
        dc.setGameScreen(this);
        gameText = new Text();
    }

    public Scene getGUI() {
        GridPane layout = new GridPane();
        Scene scene = new Scene(layout, 940, 610);
        SelectionSquare sq;


        layout.add(dc.getMapGUI(), 1, 1);

        // add a white square around the plot that mouse is currently hovering over
        sq = new SelectionSquare();
        for (Rectangle r : sq.sq) dc.getMapGUI().getChildren().add(r);

        updateText();
        util.addMovementHandlers(scene);
        util.addMovementHandlers(dc.getTownMapGUI());

        //MOUSE_MOVED event handler. When mouse moves, moves the selection square to tile that mouse is hovering over
        dc.getMapGUI().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            if (Configurations.getPhase() == 0) {
                double x = e.getX(); 
                double y = e.getY();
                sq.moveSelection(x, y);
            }
        });

        // MOUSE_PRESSED event handler. When mouse is pressed, the tile at that spot
        // is claimed for the current player, if valid.
        // Does nothing if tile invalid
        dc.getMapGUI().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (Configurations.getPhase() == 0) {
                double x = e.getX();
                double y = e.getY();
                util.claimTile(x, y, sq.getTile(x, y, dc.getMap()));
                if (Configurations.getRound() > 0) {
                    sq.remove();
                    Configurations.setPhase(1);
                }
            }
        });

        /**
         * handler that checks for keypress "p"
         * when p is pressed, current player passes his / her turn
         */
        scene.addEventHandler(KeyEvent.KEY_PRESSED, p -> {
            if (Configurations.getPhase() == 0 && p.getCode() == KeyCode.P) {
                Configurations.getCurPlayer().setPassed(true);
                util.incrementTurn();
                if (Configurations.getRound() > 0) {
                    sq.remove();
                    Configurations.setPhase(1);
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
        dc.setGameScreenGUI(getGUI());
        dc.setGameScreen(this);
    }

    /**
     * updates the gameText to present values
     */
    public void updateText() {
        updateText("");
    }
    
    public void updateText(String message) {
        Player cp = Configurations.getCurPlayer();
        gameText.setText(cp.getName() + "'s Turn. "
                + "\nMoney : $" + cp.getMoney()
                + " | Food : " + cp.getFood()
                + " | Energy : " + cp.getEnergy()
                + " | Smithore : " + cp.getSmithore()
                + "\nFood Mule : " + cp.getMule1()
                + " | Energy Mule : " + cp.getMule2()
                + " | Ore Mule : " + cp.getMule3()
                + "\nScore : " + cp.getScore());
        if (dc.getLoopService() != null) {
            gameText.setText(gameText.getText() + "\nTime : " + (120 - (int)dc.getLoopService().getTime()));
        }
        gameText.setText(gameText.getText() + "\n" + message);
    }
}
