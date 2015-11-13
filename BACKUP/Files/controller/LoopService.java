package controller;

import java.io.IOException;
import java.util.Random;

import model.Configurations;
import model.Player;
import javafx.scene.layout.Pane;

/**
 * The object that constantly runs and updates graphics
 * has an runOnFXThread and runInBackground
 * runOnFXThread deals with player movement
 * runInBackground deals with keyboard press
 *   corresponding to player movement and time
 *   incrementation
 * IMPORTANT *
 * updateDC() initializes all objects needed by LoopService
 * @author Zhijian
 * @version 1.3
 */
public class LoopService extends AbstractLoopService {

    protected static DisplayContents dc;
    protected model.landTiles.MapTiles[][] map;
    protected Pane mapGUI;
    protected PlayerMove move;
    protected double x;
    protected double y;
    protected Player cp;
    protected int vx;
    protected int vy;
    protected static double time;
    private Random rand = new Random();

    /**
     * Constructor
     */
    public LoopService() {
        dc = Configurations.getDisplayContents();
        map = dc.getMap().getAMap();
        mapGUI = dc.getMapGUI();
        move = new PlayerMove();
    }

    /**
     * Make sure to always change Configurations
     * phase back to 1 at start of each turn
     * @throws IOException ioexception
     */
    protected void runOnFXThread() throws IOException {
        if (Configurations.getPhase() == 1) {
            cp = Configurations.getCurPlayer();
            dc.getGameScreen().updateText(cp.getMessage());
            dc.getStore().updateText();
            if (time <= 120) {
                cp.move(vx, vy);
            } else {
                Util.next();
                time = 0;
            }
            //System.out.println(cp.getPlayerIcon().getCenterX() + ":" + cp.getPlayerIcon().getCenterY());
            //System.out.println(cp.getPhase());
            /**In Game Screen**/
            if (cp.getPhase() == 0) {
                cp = Configurations.getCurPlayer();
                if (cp.getX() < 5) {
                    cp.setX(5);
                }
                if (cp.getX() > 895) {
                    cp.setX(895);
                }
                if (cp.getY() < 5) {
                    cp.setY(5);
                }
                if (cp.getY() > 495) {
                    cp.setY(495);
                }
                //if you hit the town
                if (cp.getX() < 500 && cp.getX()
                    > 400 && cp.getY()
                    < 300 && cp.getY() > 200) {
                    dc.getTownMap().addPlayerToGUI(cp);
                    dc.getMainWindow().setScene(dc.getTownMapGUI());
                    cp.setX(400);
                    cp.setY(250);
                    cp.setPhase(1);
                    move.setL(0);
                    move.setR(0);
                    move.setU(0);
                    move.setD(0);
                    //if your dude has mules, it puts max 1 mule of each
                    //of them on the map when you visit your tiles
                } else {
                    int x = (int) (cp.getX() / 100);
                    int y = (int) (cp.getY() / 100);
                    //lose all your mules
                    if (!map[x][y].getOwner().equals(cp.getName())
                        && !map[x][y].getOwner().equals("None")) {
                        cp.setMule1(0);
                        cp.setMule2(0);
                        cp.setMule3(0);
                    } else {
                        //can only have one mule of that type
                        //on map, so lose 1 of those mules
                        //if go to that land that already has one
                        if (cp.getMule1() > 0 && map[x][y]
                            .getOwner().equals(cp.getName())) {
                            cp.setMule1(cp.getMule1() - 1);
                            dc.getMap().addMule(x, y, 0);
                        }
                        if (cp.getMule2() > 0 && map[x][y]
                            .getOwner().equals(cp.getName())) {
                            cp.setMule2(cp.getMule2() - 1);
                            dc.getMap().addMule(x, y, 1);
                        }
                        if (cp.getMule3() > 0 && map[x][y]
                            .getOwner().equals(cp.getName())) {
                            cp.setMule3(cp.getMule3() - 1);
                            dc.getMap().addMule(x, y, 2);
                        }
                    }
                }

                /** In Town **/
            } else if (cp.getPhase() == 1) {
                cp = Configurations.getCurPlayer();
                //System.out.println(cp.getPlayerIcon().getCenterX() + ":" + cp.getPlayerIcon().getCenterY());
                //leaving town
                if (cp.getX() < 5 || cp.getX() > 795
                    || cp.getY() < 5 || cp.getY() > 495) {
                    dc.getMainWindow().setScene(dc.getGameScreenGUI());
                    dc.getTownMap().removePlayerFromGUI(cp);
                    cp.setPhase(0);
                    move.setL(0);
                    move.setR(0);
                    move.setU(0);
                    move.setD(0);
                    if (cp.getX() < 5) {
                        cp.setX(395);
                        cp.setY(250);
                    } else if (cp.getX() > 795) {
                        cp.setX(505);
                        cp.setY(250);
                    } else if (cp.getY() < 5) {
                        cp.setX(450);
                        cp.setY(195);
                    } else if (cp.getY() > 495) {
                        cp.setX(450);
                        cp.setY(305);
                    }

                    //in pub
                } else if (cp.getX() > 475 && cp.getX() < 635
                    && cp.getY() > 327 && cp.getY() < 493) {
                    double rng = rand.nextDouble();
                    cp.setScore((int) (cp.getScore() + (50 + rng
                        * (120 - time))));
                    cp.setMoney((int) (cp.getMoney() + (50 + rng
                        * (120 - time))));
                    time = 0;
                    Util.next();
                    move.setL(0);
                    move.setR(0);
                    move.setU(0);
                    move.setD(0);

                    //in Store
                } else if (cp.getX() > 635 && cp.getX() < 800
                    && cp.getY() > 327 && cp.getY() < 493) {
                    dc.getMainWindow().setScene(dc.getStoreGUI());
                    cp.setPhase(2);
                    move.setL(0);
                    move.setR(0);
                    move.setU(0);
                    move.setD(0);
                }
            }
        }
    }

    /**
     * Runs in background
     */
    protected void runInBackground() {
        // if in game screen or in town
        if (Configurations.getPhase() == 1
            || Configurations.getPhase() == 2) {
            vx = move.getL() + move.getR();
            vy = move.getU() + move.getD();
            if (vx == 5) {
                move.setDir('r');
            }
            if (vx == -5) {
                move.setDir('l');
            }
            if (vy == 5) {
                move.setDir('d');
            }
            if (vy == -5) {
                move.setDir('u');
            }
            time = time + 0.02;
            // try {
            //     Save.save();
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
        }
    }

    /**
     * Updates DC
     */
    public void updateDC() {
        dc.setLoopService(this);
    }

    /**
     * gets time
     * @return double time
     */
    public double getTime() {
        return time;
    }

    /**
     * sets time to 120 seconds
     */
    public static void setTime() {
        time = 0;
    }
}
