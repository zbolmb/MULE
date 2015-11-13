package files;
import files.model.Configurations;
import files.model.Player;
import files.controller.Util;
import files.model.landTiles.M1Tile;
import files.model.landTiles.M2Tile;
import files.model.landTiles.M3Tile;
import files.model.landTiles.MapTiles;
import files.model.landTiles.PlainsTile;
import files.model.landTiles.RiverTile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by William on 11/12/2015.
 */
public class JunitWilliam {
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
        MapTiles t1 = new PlainsTile(1);
        t1.setMule(0);
        tiles1.add(t1);
        tiles2 = new ArrayList<>();
        MapTiles t2 = new RiverTile(0);
        t2.setMule(0);
        t2.setMule(1);
        t2.setMule(2);
        tiles2.add(t2);
        tiles3 = new ArrayList<>();
        MapTiles t3 = new M1Tile(1);
        tiles3.add(t3);
        t3.setMule(1);
        tiles4 = new ArrayList<>();
        MapTiles t4 = new M2Tile(1);
        t4.setMule(2);
        MapTiles t5 = new M3Tile(1);
        t5.setMule(2);
        tiles4.add(t4);
        tiles4.add(t5);
    }

    @Test(timeout = TIMEOUT)
    public void testProduce() throws IOException {
        Player p1 = new Player();
        p1.setOwned(tiles1);
        Player p2 = new Player();
        p2.setOwned(tiles2);
        Player p3 = new Player();
        p3.setOwned(tiles3);
        Player p4 = new Player();
        p4.setOwned(tiles4);
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        Configurations.setPlayers(players);
        Util.produce();
        assertEquals(2, players.get(0).getFood());
        assertEquals(4, players.get(1).getFood());
        assertEquals(3, players.get(1).getEnergy());
        assertEquals(0, players.get(1).getSmithore());
        assertEquals(4, players.get(2).getEnergy());
        assertEquals(7, players.get(3).getSmithore());



    }
}
