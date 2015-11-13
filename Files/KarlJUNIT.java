package files;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import files.controller.Util;
import files.model.Configurations;
import files.model.Player;

public class KarlJUNIT {

	@Before
	public void setUp() {
		ArrayList<Player> playerList = new ArrayList();
		Player p1 = new Player();
		playerList.add(p1);
		Player p2 = new Player();
		playerList.add(p2);
		Configurations.setPlayers(playerList);

	}
	@Test
	public void testAddPlayers() {

		Util.addPlayers();
		assertEquals(Util.getPlayerOrder().size(), 2);

		return;
	}

}
