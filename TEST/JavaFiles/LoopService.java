import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import landTiles.MapTiles;

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
    protected MapTiles[][] map;
    protected Pane mapGUI;
    protected PlayerMove move;
    protected double x;
    protected double y;
    protected Player cp;
    protected int vx;
    protected int vy;
    protected double time;
    private Random rand = new Random();

    public LoopService() {
        dc = Configurations.displayContents;
        map = dc.map.aMap;
        mapGUI = dc.mapGUI;
        move = new PlayerMove();
    }

    //Make sure to always change Configurations.phase back to 1 at start of each turn
    protected void runOnFXThread() {
        if (Configurations.phase == 1) {
            cp = Configurations.curPlayer;
            dc.gameScreen.updateText(cp.message);
            dc.store.updateText();
            if (time <= 120) {
                cp.move(vx, vy);
            } else {
                util.next();
                time = 0;
            }
            /**In Game Screen**/
            if (cp.phase == 0) {
                cp = Configurations.curPlayer;
                if (cp.getX() < 5) cp.setX(5);
                if (cp.getX() > 895) cp.setX(895);
                if (cp.getY() < 5) cp.setY(5);
                if (cp.getY() > 495) cp.setY(495);
                //if you hit the town
                if (cp.getX() < 500 && cp.getX() > 400 && cp.getY() < 300 && cp.getY() > 200) {
                    dc.townMap.addPlayerToGUI(cp);
                    dc.mainWindow.setScene(dc.townMapGUI);
                    cp.setX(400);
                    cp.setY(250);
                    cp.phase = 1;
                    move.l = 0;
                    move.r = 0;
                    move.u = 0;
                    move.d = 0;
                    //if your dude has mules, it puts max 1 mule of each
                    //of them on the map when you visit your tiles
                } else {
                    int x = (int) (cp.getX() / 100);
                    int y = (int) (cp.getY() / 100);
                    //lose all your mules
                    if (!map[x][y].getOwner().equals(cp.name) && !map[x][y].getOwner().equals("None")) {
                        cp.mule1 = 0;
                        cp.mule2 = 0;
                        cp.mule3 = 0;
                    } else {
                        //can only have one mule of that type on map, so lose 1 of those mules
                        //if go to that land that already has one
                        if (cp.mule1 > 0 && map[x][y].getOwner().equals(cp.name)) {
                            cp.mule1--;
                            dc.map.addMule(x, y, 0);
                        }
                        if (cp.mule2 > 0 && map[x][y].getOwner().equals(cp.name)) {
                            cp.mule2--;
                            dc.map.addMule(x, y, 1);
                        }
                        if (cp.mule3 > 0 && map[x][y].getOwner().equals(cp.name)) {
                            cp.mule3--;
                            dc.map.addMule(x, y, 2);
                        }
                    }
                }

                /** In Town **/
            } else if (cp.phase == 1) {
                cp = Configurations.curPlayer;

                //leaving town
                if (cp.getX() < 5 || cp.getX() > 795 || cp.getY() < 5 || cp.getY() > 495) {
                    dc.mainWindow.setScene(dc.gameScreenGUI);
                    dc.townMap.removePlayerFromGUI(cp);
                    cp.phase = 0;
                    move.l = 0;
                    move.r = 0;
                    move.u = 0;
                    move.d = 0;
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
                } else if (cp.getX() > 475 && cp.getX() < 635 && cp.getY() > 327 && cp.getY() < 493) {
                    cp.score += 50 + rand.nextDouble() * (120 - time);
                    time = 0;
                    util.next();
                    move.l = 0;
                    move.r = 0;
                    move.u = 0;
                    move.d = 0;

                    //in Store
                } else if (cp.getX() > 635 && cp.getX() < 800 && cp.getY() > 327 && cp.getY() < 493) {
                    dc.mainWindow.setScene(dc.storeGUI);
                    cp.phase = 2;
                    move.l = 0;
                    move.r = 0;
                    move.u = 0;
                    move.d = 0;
                }
            }
        }
    }
    protected void runInBackground() {
        // if in game screen or in town
        if (Configurations.phase == 1 || Configurations.phase == 2) {
            vx = move.l + move.r;
            vy = move.u + move.d;
            if (vx == 5) move.dir = 'r';
            if (vx == -5) move.dir = 'l';
            if (vy == 5) move.dir = 'd';
            if (vy == -5) move.dir = 'u';
            time = time + 0.02;
        }
    }

    public void updateDC() {
        dc.loopService = this;
    }
}

