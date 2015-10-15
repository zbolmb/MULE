public class Store {
    protected ArrayList<Player> players;
    protected Player currPlayer;
    protected Resource smithore;
    protected Resource energy;
    protected Resource food;
    protected Resource crystalite;

    public Store(Configurations config) {
        players = config.players;
        smithore = new Resource("Smithore", 100);
        energy = new Resource("Energy", 25);
        food = new Resource("Food", 50);
        crystalite = new Resource("crystalite", 200);
    }

    public buy(Resource resource) {
        if (resource.name.equals(smithore.name) && smithore.storeCount > 0
            && currPlayer.money > smithore) {
                currPlayer.smithore++;
                smithore.storeCount--;
                currPlayer.money = currPlayer.money - smithore.cost;
        } else if (resource.name.equals(crystalite.name) && crystalite.storeCount > 0
            && currPlayer.money > crystalite) {
                currPlayer.smithore++;
                smithore.storeCount--;
                currPlayer.money = currPlayer.money - smithore.cost;

    }

    public sell() {

    }

    public setCurrent(Player curr) {
        curPlayer = curr;
    }
}
