package files.controller;

import java.io.IOException;
import java.util.PriorityQueue;

import files.model.Configurations;
import files.model.Player;
import files.model.RandomEvent;
import files.model.landTiles.MapTiles;
import files.model.landTiles.TownTile;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * utility class
 * methods:
 * drawSelectionSq(x, y, color): draws a selection square at x, y
 * incrementTurn(): increments player turn
 * next(): robust version of incrementTurn(), also sets players start position
 *        and move then out of town if in town
 * addMovementHandlers(scene): adds movement handlers to the given scene
 * claimTile(x, y, tile): claims the tiles of current spot
 * produce(): increases production for each player
 * @author Zhijian
 *
 */
public class Util {

    protected static DisplayContents dc = Configurations.getDisplayContents();
    protected static PriorityQueue<Player> playerOrder =
            new PriorityQueue<>((Player a, Player b) -> {
            return b.getScore() - a.getScore();
        });

    protected static RandomEvent[] randomEvents = {
        new RandomEvent(100, 0, 0, "YOU FOUND A DEAD MOOSE RAT AND SOLD "
                + "THE HIDE. YOU EARNED $100."),
        new RandomEvent(100, 0, 0, "YOUR MULE WAS JUDGED 'BEST BUILT' "
                + "AT THE COLONY FAIR. YOU WON $100."),
        new RandomEvent(150, 0, 0, "A CHARITY FROM YOUR HOME-WORLD TOOK "
                + "PITY ON YOU AND SENT $150."),
        new RandomEvent(400, 0, 0, "THE MUSEUM BOUGHT YOUR ANTIQUE "
                + "PERSONAL COMPUTER FOR $400."),
        new RandomEvent(300, 0, 0, "YOUR OFFWORLD INVESTMENTS IN "
                + "ARTIFICIAL DUMBNESS PAID $300 IN DIVIDENDS."),
        new RandomEvent(-300, 0, 0, "YOUR SPACE GYPSY INLAWS MADE A "
                + "MESS OF THE TOWN. IT COST YOU $300 TO CLEAN IT UP."),
        new RandomEvent(-200, 0, 0, "YOUR CHILD WAS BITTEN BY A BAT "
                + "LIZARD AND THE HOSPITAL BILL COST YOU $200."),
        new RandomEvent(-200, 0, 0, "YOU LOST $200 BETTING ON THE "
                + "TWO-LEGGED KAZINGA RACES."),
        new RandomEvent(-150, 0, 0, "ONE OF YOUR MULES LOST A BOLT. "
                + "REPAIRS COST YOU $150."),
        new RandomEvent(-200, 0, 0, "FLYING CAT-BUGS ATE THE ROOF "
                + "OFF YOUR HOUSE. REPAIRS COST $200.")
    };

    /**
     * method that highlights a tile the color of current player to indicate
     * it is that players land
     * @param x coord of mouse
     * @param y coord of mouse
     * @param c the color to highlight the tile
     * @return the 4 edges of the square (how it highlights)
     */
    public static Rectangle[] drawSelectionSq(double x, double y, Color c) {
        int rx = ((int) (x / 100)) * 100;
        int ry = ((int) (y / 100)) * 100;
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
     * @throws IOException exception
     */
    public static void next() throws IOException {
        Util.incrementTurn();
        Player cp = Configurations.getCurPlayer();
        cp.moveTo(cp.getStartX(), cp.getStartY());
        if (cp.getPhase() != 0) {
            dc.getTownMap().removePlayerFromGUI(cp);
            cp.setPhase(0);
        }
        dc.getMainWindow().setScene(dc.getGameScreenGUI());
    }

    /**
     * increments turn
     * @throws IOException exception
     */
    public static void incrementTurn() throws IOException {
        if (Configurations.getRound() == 0) {
            buyTurnIncre();
        } else {
            movePhaseTurnIncre();
        }
    }

    /**
     * gives turns for buying to players
     * @throws IOException exception
     */
    private static void buyTurnIncre() throws IOException {
        if (playerOrder.isEmpty()) {
            for (Player p : Configurations.getPlayers()) {
                if (p.getMoney() > 300 && !p.isPassed()) {
                    playerOrder.add(p);
                }
            }



            if (playerOrder.isEmpty()) {
                Configurations.setRound(Configurations.getRound() + 1);

                // Applying random event to player 1 during initial game start
                Configurations.getCurPlayer().setMessage(applyRandomEvent());
                dc.getGameScreen().updateText(
                        Configurations.getCurPlayer().getMessage());

                movePhaseTurnIncre();
                return;
            }
        }
        Configurations.setCurPlayer(playerOrder.remove());
        if (Configurations.getCurPlayer().isPassed()) {
            buyTurnIncre();
        }
        dc.getGameScreen().updateText();
    }

    /**
     * moves / increments turn based off of players priority queue
     * @throws IOException exception
     */
    private static void movePhaseTurnIncre() throws IOException {
        if (playerOrder.isEmpty()) {
            for (Player p : Configurations.getPlayers()) {
                playerOrder.add(p);
            }
            Configurations.setRound(Configurations.getRound() + 1);
            if (Configurations.getPhase() == 1) {
                produce();
            }
        }
        Configurations.setCurPlayer(playerOrder.remove());
        if (Configurations.getPhase() != 0) {
            if (Math.random() < .27) {
                Configurations.getCurPlayer().setMessage(applyRandomEvent());
                dc.getGameScreen().updateText(
                        Configurations.getCurPlayer().getMessage());
            } else {
                Configurations.getCurPlayer().setMessage("");
                dc.getGameScreen().updateText(
                        Configurations.getCurPlayer().getMessage());
            }
        } else {
            dc.getGameScreen().updateText();
        }
    }

    /**
     * adds players in Util
     */
    public static void addPlayers() {
        for (Player p : Configurations.getPlayers()) {
            playerOrder.add(p);
        }
    }

    /**
     * Handlers check for keypresses left, right, up, down arrow keys
     * If left is pressed, add value of-5 to left move (decrease X to move left)
     * right is pressed, add 5 to right move (increase X to move up)
     * up is pressed, add -5 to up (decrease Y to move up)
     * down is pressed, add 5 to down (increase Y to move down)
     * @param scene Scene
     */
    public static void addMovementHandlers(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, k -> {
                if (k.getCode() == KeyCode.LEFT) {
                    dc.getLoopService().move.setL(-5);
                }
                if (k.getCode() == KeyCode.RIGHT) {
                    dc.getLoopService().move.setR(5);
                }
                if (k.getCode() == KeyCode.UP) {
                    dc.getLoopService().move.setU(-5);
                }
                if (k.getCode() == KeyCode.DOWN) {
                    dc.getLoopService().move.setD(5);
                }
            });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, k -> {
                if (k.getCode() == KeyCode.LEFT) {
                    dc.getLoopService().move.setL(0);
                }
                if (k.getCode() == KeyCode.RIGHT) {
                    dc.getLoopService().move.setR(0);
                }
                if (k.getCode() == KeyCode.UP) {
                    dc.getLoopService().move.setU(0);
                }
                if (k.getCode() == KeyCode.DOWN) {
                    dc.getLoopService().move.setD(0);
                }
            });
    }

    /**
     * claims tiles for players
     * @param x double
     * @param y double
     * @param tile MapTiles
     * @throws IOException exception
     */
    public static void claimTile(double x, double y, MapTiles tile)
            throws IOException {
        if (Configurations.getRound() == 0
                && Configurations.getCurPlayer().getMoney() < 300) {
            return;
        }
        DisplayContents dc = Configurations.getDisplayContents();
        if (!(tile instanceof TownTile) && tile.getOwner().equals("None")) {
            Player cp = Configurations.getCurPlayer();
            if (cp.getOwned().size() <= 0) {
                int rx = (int) (x / 100) * 100;
                int ry = (int) (y / 100) * 100;
                cp.setStartX(rx + 0.5 * MapTiles.getW());
                cp.setStartY(ry + 0.5 * MapTiles.getH());
                cp.setPlayerIcon(new Circle(
                        cp.getStartX(),
                        cp.getStartY(),
                        10,
                        cp.getColor()));
                dc.getMapGUI().getChildren().add(cp.getPlayerIcon());
            }
            cp.getOwned().add(tile);
            tile.setOwner(cp.getName());
            if (Configurations.getRound() == 0) {
                cp.setMoney(cp.getMoney() - 300);
            }
            Rectangle[] sq = Util.drawSelectionSq(x, y, cp.getColor());

            for (Rectangle r : sq) {
                dc.getMapGUI().getChildren().add(r);
            }
            incrementTurn();
        }
    }

    /**
     * produces game values
     * @throws IOException exception
     */
    public static void produce() throws IOException {
        for (Player p : Configurations.getPlayers()) {
            for (MapTiles tile : p.getOwned()) {
                for (int i = 0; i < tile.getMules().length
                        && p.getEnergy() > 0; i++) {
                    if (tile.getMules()[i]) {
                        if (i == 0) {
                            p.setFood(p.getFood() + tile.getFood());
                            p.setEnergy(p.getEnergy() - 1);
                        } else if (i == 1) {
                            p.setEnergy(p.getEnergy() + tile.getEnergy());
                            p.setEnergy(p.getEnergy() - 1);
                        } else if (i == 2){
                            p.setSmithore(p.getSmithore()
                                    + tile.getOre());
                            p.setEnergy(p.getEnergy() - 1);
                        } else {
                            p.setCrystite(p.getCrystite() + 1);
                            p.setEnergy(p.getEnergy() - 1);
                        }
                    }
                }
            }
        }
        // Save.save();
    }

    /**
     * generates random events to players
     * @return String message of random event
     */
    public static String applyRandomEvent() {
        int rand = (int) (Math.random() * randomEvents.length);

        Player cp = Configurations.getCurPlayer();
        int money = randomEvents[rand].getMoney();
        int food = randomEvents[rand].getFood();
        int energy = randomEvents[rand].getEnergy();

        if (playerOrder.size() == 0) {
            while (money < 0 || food < 0 || energy < 0) {
                rand = (int) (Math.random() * randomEvents.length);
                money = randomEvents[rand].getMoney();
                food = randomEvents[rand].getFood();
                energy = randomEvents[rand].getEnergy();
            }
        }

        cp.setMoney(cp.getMoney() + money);
        cp.setFood(cp.getFood() + food);
        cp.setEnergy(cp.getEnergy() + energy);

        if (cp.getMoney() < 0) {
            cp.setMoney(0);
        } else if (cp.getFood() < 0) {
            cp.setFood(0);
        } else if (cp.getEnergy() < 0) {
            cp.setEnergy(0);
        }

        return randomEvents[rand].getMessage();
    }

    /**
     * gets player order
     * @return PriorityQueue playerOrder
     */
    public static PriorityQueue<Player> getPlayerOrder() {
        return playerOrder;
    }
}
