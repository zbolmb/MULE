import java.util.ArrayList;

public class Production {
    protected ArrayList<MapTiles> own;
    protected ArrayList<Player> players;
    protected Player player;

    public Production(Configurations config) {
        players = config.players;
    }

    public void produce() {
        own = player.owned;
        for (MapTiles tile : own) {
            if (player.energy == 0) {
                break;
            }
            player.food = player.food + tile.getFood();
            if (tile.mule3) {
                player.food = player.food + tile.getFood();
            }
            player.energy = player.energy + tile.getEnergy();
            if (tile.mule2) {
                player.energy = player.energy + tile.getEnergy();
            }
            player.smithore = player.smithore + tile.getOre();
            if (tile.mule3) {
                player.smithore = player.smithore + tile.getOre();
            }
            player.energy--;
        }
    }

    public void setCurrent(Player p) {
        player = p;
    }
}
