import java.util.ArrayList;

/**
 * TurnTracker is a class that keeps track of players and the current player
 * Only used for buy phase of MULE
 * @author Zhijian
 *
 */
public class TurnTracker {
    protected ArrayList<Player> players;
    protected int round;
    protected int curPlayer;
    protected boolean buyPhase;
    
    public TurnTracker(ArrayList<Player> players) {
        this.players = new ArrayList<>();
        for (Player p : players) {
            this.players.add(p);
        }
        round = 0;
        curPlayer = 0;
        buyPhase = false;
    }
    
    public boolean nextTurn() {
        if (players.size() == 0) return false;
        curPlayer++;
        if (curPlayer == players.size()) {
            curPlayer = 0;
            increRound();
        }
        while (players.size() != 0 && players.get(curPlayer).money < 300) {
            players.remove(curPlayer);
            if (curPlayer == players.size()) {
                curPlayer = 0;
                increRound();
            }
        }
        if (players.size() == 0) return true;
        return false;
    }
    
    public boolean pass() {
        players.remove(curPlayer);
        return nextTurn();
    }
    
    public Player getCurPlayer() {
        if (players.size() == 0) return null;
        return players.get(curPlayer);
    }
    
    public void increRound() {
        if (round == 1) {
            buyPhase = true;
            return;
        }
        round++;
    }
} 
