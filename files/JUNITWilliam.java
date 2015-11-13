package files;
import files.model.Configurations;
import files.model.Player;
import files.model.landTiles.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

/**
 * Created by William on 11/12/2015.
 */
public class JUNITWilliam {
    public static final int TIMEOUT = 200;
    private ArrayList<Player> players;
    private ArrayList<MapTiles> tiles1;
    private ArrayList<MapTiles> tiles2;
    private ArrayList<MapTiles> tiles3;
    private ArrayList<MapTiles> tiles4;

    @Before
    public void setUp() {
        players = new ArrayList<>();
        tiles1 = new ArrayList<>();
        tiles1.add((MapTiles) new PlainsTile(1));
        tiles2 = new ArrayList<>();
        tiles2.add((MapTiles) new RiverTile(0));
        tiles3 = new ArrayList<>();
        tiles3.add((MapTiles) new M1Tile(1));
        tiles4 = new ArrayList<>();
        tiles4.add((MapTiles) new M2Tile(1));
        tiles4.add((MapTiles) new M3Tile(1));
    }

    @Test(timeout = TIMEOUT)
    public void testProduce() {
        Player p1 = new Player();
        p1.setOwned(tiles1);
    }
}
