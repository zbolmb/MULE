package files.controller;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import files.model.Configurations;
import files.model.GameScreen;
import files.model.Player;
import files.model.SelectionSquare;
import files.view.GameScreenView;

public class GameScreenController {

    GameScreen screen;
    GameScreenView screenView;
    GameMapController mapController;
    TownMapController townMapController;
    
    public GameScreenController() {
        screen = new GameScreen();
        screenView = new GameScreenView(new GridPane());
    }
    
    /**
     * setup
     * @param townMapController townMapController
     * @param mapController mapController
     */
    public void setup(GameMapController mapController
            , TownMapController townMapController) {
        this.mapController = mapController;
        this.townMapController = townMapController;
    }
    

    /**
     * getGui method
     * @return data
     */
    public void updateView() {
        SelectionSquare sq;

        screenView.getLayout().add(mapController.getMapView(), 1, 1);

        if (Configurations.getRound() <= 0) {
            sq = new SelectionSquare();
            for (Rectangle r : sq.getSq()) {
                mapController.add(r);
            }
            mapController.getMapView().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
                    if (Configurations.getPhase() == 0) {
                        double x = e.getX();
                        double y = e.getY();
                        sq.moveSelection(x, y);
                    }
                });

            // is claimed for the current player, if valid.
            // Does nothing if tile invalid
            mapController.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                    if (Configurations.getPhase() == 0) {
                        double x = ((MouseEvent) e).getX();
                        double y = ((MouseEvent) e).getY();
                        try {
                            Util.claimTile(x, y, sq.getTile(x, y, mapController.getMap()));
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
            screenView.addEventHandler(KeyEvent.KEY_PRESSED, p -> {
                    if (Configurations.getPhase() == 0
                            && ((KeyEvent) p).getCode() == KeyCode.P) {
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
        Util.addMovementHandlers(screenView);
        Util.addMovementHandlers(townMapController.getView());



        screenView.getLayout().setHgap(20);
        screenView.getLayout().setVgap(20);

        screenView.getLayout().add(screenView.getGameText(), 1, 0);
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
        //System.out.println(cp.getName());
        screen.setGameText(cp.getName() + "'s Turn. "
                + "\nMoney : $" + cp.getMoney()
                + " | Food : " + cp.getFood()
                + " | Energy : " + cp.getEnergy()
                + " | Smithore : " + cp.getSmithore()
                + "\nFood Mule : " + cp.getMule1()
                + " | Energy Mule : " + cp.getMule2()
                + " | Ore Mule : " + cp.getMule3()
                + "\nScore : " + cp.getScore()
                + "\nTime : " + (120 - (int)Configurations.getLoopService().getTime())
                + "\n" + message);
        screenView.setGameText(screen.getGameText());
    }
    
    /**
     * addPlayer to town method
     * @param p player
     */
    public void addPlayerToTown(Player p) {
        screenView.remove(p.getPlayerIcon());
        townMapController.add(p.getPlayerIcon());

    }

    /**
     * removePlayerfromgui method
     * @param p player
     */
    public void removePlayerFromTown(Player p) {
        townMapController.remove(p.getPlayerIcon());
        screenView.add(p.getPlayerIcon());
    }
    
    /**
     * getter for view
     * @return GameScreenView the view
     */
    public GameScreenView getView() {
        return screenView;
    }
}
