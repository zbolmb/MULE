import java.util.ArrayList;

/**
 * Created by willi on 9/28/2015.
 */
public class TownTurn extends TurnTracker {
    protected ArrayList<Player> players;
    protected int round;

    public TownTurn(ArrayList<Player> players) {
        super(players);
    }

    public Player getRichGuy() {
        if (players.size() == 0) {
            return null;
        }
        Player moneyMan = players.get(0);
        for (int i = 0; i < players.size(); i++) {
            Player currPlayer = players.get(i);
            if (currPlayer.money > moneyMan.money) {
                moneyMan = currPlayer;
            }
        }
        players.remove(moneyMan);
        return moneyMan;
    }


}