package files;

/**
 * JUnit tests sell method in files.model.townTiles.Store
 * @author Zhijian Li
 *
 */

import files.model.Configurations;
import files.model.Player;
import files.model.Store;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class JUnitLi {

    private Store store;

    @Before
    public void setUp() {
        Player p1 = new Player("Jingers", "Orc", Color.BLACK);
        Player p2 = new Player("Will", "Elin", Color.YELLOW);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        Configurations.setPlayers(players);
        Configurations.setDifficulty("Easy");
        Configurations.setMapType("River");
        Configurations.setNumPlayers(2);
        Configurations.setCurPlayer(p1);
        Configurations.getCurPlayer().setCrystite(10);
        Configurations.getCurPlayer().setEnergy(10);
        Configurations.getCurPlayer().setFood(10);
        Configurations.getCurPlayer().setSmithore(10);
        store = new Store();

    }

    @Test(timeout = 200)
    public void testSell() {
        Configurations.getCurPlayer().setMoney(0);
        store.sell(0);
        assertTrue(1 == store.getSmithore());
        assertTrue(9 == Configurations.getCurPlayer().getSmithore());
        assertTrue(Configurations.getCurPlayer().getMoney() == 100);

        Configurations.getCurPlayer().setMoney(0);
        store.sell(2);
        assertTrue(17 == store.getEnergy());
        assertTrue(9 == Configurations.getCurPlayer().getEnergy());
        assertTrue(Configurations.getCurPlayer().getMoney() == 25);

        Configurations.getCurPlayer().setMoney(0);
        store.sell(3);
        assertTrue(9 == Configurations.getCurPlayer().getFood());
        assertTrue(17 == store.getFood());
        assertTrue(Configurations.getCurPlayer().getMoney() == 50);

        //now tests if player has none of that thing

        Configurations.getCurPlayer().setSmithore(0);
        Configurations.getCurPlayer().setCrystite(0);
        Configurations.getCurPlayer().setFood(0);
        Configurations.getCurPlayer().setEnergy(0);
        Configurations.getCurPlayer().setMoney(0);
        store.setSmithore(0);
        store.setCrystite(0);
        store.setEnergy(0);
        store.setFood(0);

        store.sell(0);
        assertTrue(0 == store.getSmithore());
        assertTrue(0 == Configurations.getCurPlayer().getSmithore());
        assertTrue(Configurations.getCurPlayer().getMoney() == 0);

        store.sell(2);
        assertTrue(0 == store.getEnergy());
        assertTrue(0 == Configurations.getCurPlayer().getEnergy());
        assertTrue(Configurations.getCurPlayer().getMoney() == 0);

        store.sell(3);
        assertTrue(0 == Configurations.getCurPlayer().getFood());
        assertTrue(0 == store.getFood());
        assertTrue(Configurations.getCurPlayer().getMoney() == 0);

    }
}
