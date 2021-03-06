package files;

import files.model.Configurations;
import files.model.Player;
import files.model.Store;
import javafx.scene.paint.Color;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


/**
 * Created by williamsu on 11/12/15.
 */
public class JUnitSu {
    private Store store;
    @Before
    public void setUp() {
        Player p1 = new Player("James", "Elin", Color.BLUE);
        Player p2 = new Player("Karl", "Orc", Color.GREEN);
        ArrayList<Player> listOfPlayer = new ArrayList<>();
        listOfPlayer.add(p1);
        listOfPlayer.add(p2);
        Configurations.setDifficulty("Easy");
        Configurations.setNumPlayers(2);
        Configurations.setPlayers(listOfPlayer);
        Configurations.setCurPlayer(p1);
        store = new Store();
        store.setSmithore(10);
        store.setCrystite(10);
        store.setEnergy(10);
        store.setFood(10);
    }

    @Test
    public void testBuy() {
        store.buy(0);
        assertTrue(9 == store.getSmithore());
        assertTrue(1 == Configurations.getCurPlayer().getSmithore());
        assertTrue(Configurations.getCurPlayer().getMoney() == 900);

        store.buy(1);
        assertTrue(9 == store.getCrystite());
        assertTrue(1 == Configurations.getCurPlayer().getCrystite());
        assertTrue(Configurations.getCurPlayer().getMoney() == 700);

        store.buy(2);
        assertTrue(9 == store.getEnergy());
        assertTrue(5 == Configurations.getCurPlayer().getEnergy());
        assertTrue(Configurations.getCurPlayer().getMoney() == 675);

        store.buy(3);
        assertTrue(9 == store.getFood());
        assertTrue(1 == Configurations.getCurPlayer().getFood());
        assertTrue(Configurations.getCurPlayer().getMoney() == 625);

        //Testing for no item in store

        store.setSmithore(0);
        store.setCrystite(0);
        store.setEnergy(0);
        store.setFood(0);

        Configurations.getCurPlayer().setMoney(1000);

        store.buy(0);
        assertTrue(0 == store.getSmithore());
        assertTrue(1 == Configurations.getCurPlayer().getSmithore());
        assertTrue(Configurations.getCurPlayer().getMoney() == 1000);

        store.buy(1);
        assertTrue(0 == store.getCrystite());
        assertTrue(1 == Configurations.getCurPlayer().getCrystite());
        assertTrue(Configurations.getCurPlayer().getMoney() == 1000);

        store.buy(2);
        assertTrue(0 == store.getEnergy());
        assertTrue(5 == Configurations.getCurPlayer().getEnergy());
        assertTrue(Configurations.getCurPlayer().getMoney() == 1000);

        store.buy(3);
        assertTrue(0 == store.getFood());
        assertTrue(1 == Configurations.getCurPlayer().getFood());
        assertTrue(Configurations.getCurPlayer().getMoney() == 1000);

    }

}
