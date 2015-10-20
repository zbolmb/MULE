import java.util.ArrayList;
import landTiles.MapTiles;

/**
 * BuyPhaseTurnTracker is a class that keeps track of players and the current player
 * Only used for buy phase of MULE
 * @author Zhijian
 *
 */
public class BuyPhaseTurnTracker extends TurnTracker{
    protected boolean buyPhase;

    public BuyPhaseTurnTracker(ArrayList<Player> players) {
        super(players);
        buyPhase = false;
    }

    public boolean pass() {
        if (players.size() == 0) return true;
        if (players.get(curPlayer).owned.size() != 0) players.remove(curPlayer);
        if (players.size() == 0) return true;
        return nextTurn();
    }
    
    public void increRound() {
        if (round == 1) {
            buyPhase = true;
            return;
        }
        round++;
    }
} 
