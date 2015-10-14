import java.util.ArrayList;

/**
 * TurnTracker is a class that keeps track of players and the current player
 * @author Zhijian
 *
 */
public abstract class TurnTracker {
    protected ArrayList<Player> players;
    protected int round;
    protected int curPlayer;

    public TurnTracker(ArrayList<Player> players) {
        this.players = new ArrayList<>();
        for (Player p : players) {
            this.players.add(p);
        }
        round = 0;
        curPlayer = 0;
    }

    public boolean nextTurn() {
        if (players.size() == 0) return false;
        curPlayer++;
        if (curPlayer >= players.size()) {
            curPlayer = 0;
            increRound();
        }
        while (players.size() != 0 && players.get(curPlayer).money < 300) {
            players.remove(curPlayer);
            if (curPlayer >= players.size()) {
                curPlayer = 0;
                increRound();
            }
        }
        if (players.size() == 0) return true;
        return false;
    }

    public Player getCurPlayer() {
        if (players.size() == 0) return null;
        return players.get(curPlayer);
    }

    public void increRound() {
        round++;
    }
} 
