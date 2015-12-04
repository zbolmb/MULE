package files.model;

import files.controller.DisplayContents;
import files.controller.LoopService;
import files.controller.Util;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

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

    private static DisplayContents dc;
    private Text gameText;

    /**
     * GameScreen method
     */
    public GameScreen() {
        dc = Configurations.getDisplayContents();
        dc.setGameScreen(this);
        gameText = new Text();
    }

    /**
     * getGui method
     * @return data
     */
    public Scene getGUI() {
        GridPane layout = new GridPane();
        Scene scene = new Scene(layout, 940, 610);
        SelectionSquare sq;


        layout.add(dc.getMapGUI(), 1, 1);

        if (Configurations.getRound() <= 0) {
            sq = new SelectionSquare();
            for (Rectangle r : sq.sq) {
                dc.getMapGUI().getChildren().add(r);
            }
            dc.getMapGUI().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                    if (Configurations.getPhase() == 0) {
                        double x = e.getX();
                        double y = e.getY();
                        sq.moveSelection(x, y);
                    }
                });

            // is claimed for the current player, if valid.
            // Does nothing if tile invalid
            dc.getMapGUI().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                    if (Configurations.getPhase() == 0) {
                        double x = e.getX();
                        double y = e.getY();
                        try {
                            Util.claimTile(x, y, sq.getTile(x, y, dc.getMap()));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
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
                    if (Configurations.getPhase() == 0
                            && p.getCode() == KeyCode.P) {
                        Configurations.getCurPlayer().setPassed(true);
                        try {
                            Util.incrementTurn();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (Configurations.getRound() > 0) {
                            sq.remove();
                            Configurations.setPhase(1);
                        }
                    }
                });
        }

        updateText();
        Util.addMovementHandlers(scene);
        Util.addMovementHandlers(dc.getTownMapGUI());



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

    /**
     * updateText method
     * @param message message
     */
    public void updateText(String message) {
        Player cp = Configurations.getCurPlayer();
        gameText.setText(cp.getName() + "'s Turn. "
                + "\nMoney : $" + cp.getMoney()
                + " | Food : " + cp.getFood()
                + " | Energy : " + cp.getEnergy()
                + " | Smithore : " + cp.getSmithore()
                + " | Crystite : " + cp.getCrystite()
                + "\nFood Mule : "
                + cp.getMule1()
                + " | Energy Mule : " + cp.getMule2()
                + " | Ore Mule : " + cp.getMule3()
                + " | Crystite Mule : " + cp.getMule4()
                + "\nScore : " + cp.getScore());
        if (dc.getLoopService() != null) {
            gameText.setText(gameText.getText() + "\nTime : "
                    + (120 - (int) dc.getLoopService().getTime()));
        }
        gameText.setText(gameText.getText() + "\n" + message);
    }
}
