import java.util.ArrayList;

public class Production {
    protected ArrayList<MapTiles> owned;
    protected ArrayList<Player> players;
    protected Player curr;

    public Production(Configurations config) {
        players = config.players;
    }

    public void produce() {
        owned = curr.owned;
        for (MapTiles tile : owned) {
            if (curr.energy == 0) {
                break;
            }
            curr.food = curr.food + tile.getFood();
            curr.energy = curr.energy + tile.getEnergy();
            curr.smithore = curr.smithore + tile.getOre();
            curr.energy--;
        }
    }

    public void setCurrent(Player curr) {
        curr = curr;
    }
}
