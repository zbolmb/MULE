import java.util.PriorityQueue;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import landTiles.*;

/**
 * utility class
 * methods:
 * drawSelectionSq(x, y, color): draws a selection square at x, y
 * incrementTurn(): increments player turn
 * next(): a robust version of incrementTurn(), also sets players starting position
 *        and move then out of town if in town
 * addMovementHandlers(scene): adds movement handlers to the given scene
 * claimTile(x, y, tile): claims the tiles of current spot
 * produce(): increases production for each player
 * @author Zhijian
 *
 */
public class util {

    static DisplayContents dc = Configurations.displayContents;
    static PriorityQueue<Player> playerOrder = new PriorityQueue<>(4, (Player a, Player b) -> {
        return b.score - a.score;
    });
    

    /**
     * method that highlights a tile the color of current player to indicate it is that players land
     * @param x coord of mouse
     * @param y coord of mouse
     * @param c the color to highlight the tile
     * @return the 4 edges of the square (how it highlights)
     */
    public static Rectangle[] drawSelectionSq(double x, double y, Color c) {
        int rx = (int)(x / 100) * 100;
        int ry = (int)(y / 100) * 100;
        Rectangle r = new Rectangle(10, 100, c);
        Rectangle l = new Rectangle(10, 100, c);
        Rectangle u = new Rectangle(100, 10, c);
        Rectangle d = new Rectangle(100, 10, c);

        r.setX(rx);
        r.setY(ry);    
        l.setX(rx + 90);
        l.setY(ry);        
        u.setX(rx);
        u.setY(ry);        
        d.setX(rx);
        d.setY(ry + 90);

        Rectangle[] ret = new Rectangle[] {r, l, u, d};

        return ret;
    }

    /**
     * Goes to next players turn
     * more robust than incrementTurn
     * @param p current player
     */
    public static void next() {
        util.incrementTurn();
        Player cp = Configurations.curPlayer;
        cp.moveTo(cp.startX, cp.startY);
        if (cp.phase != 0) {
            dc.townMap.removePlayerFromGUI(cp);
            cp.phase = 0;
        }
        dc.mainWindow.setScene(dc.gameScreenGUI);
    }
    
    public static void incrementTurn() {
        if (Configurations.round == 0) {
            buyTurnIncre();
        } else {
            movePhaseTurnIncre();
        }
    }

    private static void buyTurnIncre() {
        if (playerOrder.isEmpty()) {
            for (Player p : Configurations.players) {
                if (p.money > 300 && !p.passed) playerOrder.add(p);
            }
            if (playerOrder.isEmpty()) {
                Configurations.round++;
                movePhaseTurnIncre();
                return;
            }
        }
        Configurations.curPlayer = playerOrder.remove();
        if (Configurations.curPlayer.passed) buyTurnIncre();
        dc.gameScreen.updateText();
    }

    private static void movePhaseTurnIncre() {
        if (playerOrder.isEmpty()) {
            for (Player p : Configurations.players) playerOrder.add(p);
            Configurations.round++;
            if (Configurations.phase == 1) produce();
        }
        Configurations.curPlayer = playerOrder.remove();
        dc.gameScreen.updateText();
    }

    /**
     * Handlers check for keypresses left, right, up, down arrow keys
     * If left is pressed, add value of-5 to left move (decrease X to move left)
     *    right is pressed, add 5 to right move (increase X to move up)
     *    up is pressed, add -5 to up (decrease Y to move up)
     *    down is pressed, add 5 to down (increase Y to move down)
     */
    public static void addMovementHandlers(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, k -> {
            if (k.getCode() == KeyCode.LEFT) dc.loopService.move.l = -5;
            if (k.getCode() == KeyCode.RIGHT) dc.loopService.move.r = 5;
            if (k.getCode() == KeyCode.UP) dc.loopService.move.u = -5;
            if (k.getCode() == KeyCode.DOWN) dc.loopService.move.d = 5;
        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, k -> {
            if (k.getCode() == KeyCode.LEFT) dc.loopService.move.l = 0;
            if (k.getCode() == KeyCode.RIGHT) dc.loopService.move.r = 0;
            if (k.getCode() == KeyCode.UP) dc.loopService.move.u = 0;
            if (k.getCode() == KeyCode.DOWN) dc.loopService.move.d = 0;
        });
    }

    public static void claimTile(double x, double y, MapTiles tile) {
        if (Configurations.round == 0 && Configurations.curPlayer.money < 300) {
            return;
        }
        DisplayContents dc = Configurations.displayContents;
        if (!(tile instanceof townTile) && tile.getOwner().equals("None")) {
            Player cp = Configurations.curPlayer;
            if (cp.owned.size() <= 0) {
                int rx = (int)(x / 100) * 100;
                int ry = (int)(y / 100) * 100;
                cp.startX = rx + 0.5 * MapTiles.getW();
                cp.startY = ry + 0.5 * MapTiles.getH();
                cp.playerIcon = new Circle(
                        cp.startX
                        , cp.startY
                        , 10
                        , cp.color);
                dc.mapGUI.getChildren().add(cp.playerIcon);
            }
            cp.owned.add(tile);
            tile.setOwner(cp.name);
            if (Configurations.round == 0) {
                cp.money -= 300;
            }
            Rectangle[] sq = util.drawSelectionSq(x, y, cp.color);

            for (Rectangle r : sq) dc.mapGUI.getChildren().add(r);
            incrementTurn();
        }
    }

    public static void produce() {
        for (Player p : Configurations.players) {
            for (MapTiles tile : p.owned) {
                for (int i = 0; i < tile.getMules().length && p.energy > 0; i++) {
                    if (tile.getMules()[i]) {
                        if (i == 0) {
                            p.food += tile.getFood();
                            p.energy--;
                        } else if (i == 1) {
                            p.energy += tile.getEnergy();
                            p.energy--;
                        } else {
                            p.smithore += tile.getOre();
                            p.energy--;
                        }
                    }
                }
            }
        }
    }
}
