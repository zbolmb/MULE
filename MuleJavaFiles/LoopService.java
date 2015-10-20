import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import landTiles.MapTiles;


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

    //Make sure to always change Configurations.phase back to 1 at end of each turn
    protected void runOnFXThread() {
        if (Configurations.phase == 1) {
            cp = Configurations.curPlayer;
            if (time <= 120) {
                cp.move(vx, vy);
            } else {
                util.next(cp);
                time = 0;
            }
            //In Game Screen
            if (cp.phase == 0) {
                cp = Configurations.curPlayer;
                if (cp.getX() < 5) cp.setX(5);
                if (cp.getX() > 895) cp.setX(895);
                if (cp.getY() < 5) cp.setY(5);
                if (cp.getY() > 495) cp.setY(495);
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
                } else {
                    //if your dude has mules, it puts them on the map when you visit your tiles
                    int x = (int) (cp.getX() / 100);
                    int y = (int) (cp.getY() / 100);
                    if (cp.mule1 > 0 && map[x][y].getOwner().equals(cp.name)) {
                        cp.mule1--;
                        map[x][y].setMule(0);
                        Rectangle mule1 = new Rectangle(x * 100 + 20, y * 100 + 55, 15, 33);
                        mule1.setFill(cp.color);
                        mapGUI.getChildren().add(mule1);
                    }
                    if (cp.mule2 > 0 && map[x][y].getOwner().equals(cp.name)) {
                        cp.mule1--;
                        map[x][y].setMule(1);
                        Rectangle mule2 = new Rectangle(x * 100 + 40, y * 100 + 55, 15, 33);
                        mule2.setFill(cp.color);
                        mapGUI.getChildren().add(mule2);
                    }
                    if (cp.mule3 > 0 && map[x][y].getOwner().equals(cp.name)) {
                        cp.mule1--;
                        map[x][y].setMule(2);
                        Rectangle mule3 = new Rectangle(x * 100 + 60, y * 100 + 55, 15, 33);
                        mule3.setFill(cp.color);
                        mapGUI.getChildren().add(mule3);
                    }
                }

            // In Town
            } else if (cp.phase == 1) {
                cp = Configurations.curPlayer;
                
                //leaving town
                if (cp.getX() < 5 || cp.getX() > 795 || cp.getY() < 5 || cp.getY() > 495) {
                    dc.mainWindow.setScene(dc.gameScreenGUI);
                    dc.townMap.removePlayerFromGUI(cp);
                    cp.phase = 0;
                    cp.setX(395);
                    cp.setY(250);
                    move.l = 0;
                    move.r = 0;
                    move.u = 0;
                    move.d = 0;

                    //in pub
                } else if (cp.getX() > 475 && cp.getX() < 635 && cp.getY() > 327 && cp.getY() < 493) {
                    cp.score += 50 + rand.nextDouble() * (120 - time);
                    util.next(cp);
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

