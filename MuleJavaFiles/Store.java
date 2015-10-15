import java.util.ArrayList;
public class Store {
    protected ArrayList<Player> players;
    protected Player currPlayer;
    protected Resource smithore;
    protected Resource energy;
    protected Resource food;
    protected Resource crystalite;
    protected Resource mule;

    public Store(Configurations config) {
        players = config.players;
        smithore = new Resource("Smithore", 100);
        energy = new Resource("Energy", 25);
        food = new Resource("Food", 50);
        crystalite = new Resource("crystalite", 200);
        mule = new Resource("Mule", 300);
    }

    public void buy(Resource resource) {
        if (resource.name.equals(smithore.name) && smithore.storeCount > 0
            && currPlayer.money > smithore.cost) {
                currPlayer.smithore++;
                smithore.storeCount--;
                currPlayer.money = currPlayer.money - smithore.cost;
        } else if (resource.name.equals(crystalite.name) && crystalite.storeCount > 0
            && currPlayer.money > crystalite.cost) {
                currPlayer.crystalite++;
                crystalite.storeCount--;
                currPlayer.money = currPlayer.money - crystalite.cost;

        } else if (resource.name.equals(energy.name) && energy.storeCount > 0
            && currPlayer.money > energy.cost) {
                currPlayer.energy++;
                energy.storeCount--;
                currPlayer.money = currPlayer.money - energy.cost;
        } else if (resource.name.equals(food.name) && food.storeCount > 0
            && currPlayer.money > food.cost) {
                currPlayer.food++;
                food.storeCount--;
                currPlayer.money = currPlayer.money - food.cost;

        } else {
            System.out.println("Not enough money or store does not carry");
        }

    }

    public void sell(Resource resource) {
        if (resource.name.equals(smithore.name) && currPlayer.smithore > 0) {
                currPlayer.smithore--;
                smithore.storeCount++;
                currPlayer.money = currPlayer.money + smithore.cost;
        } else if (resource.name.equals(crystalite.name) && currPlayer.crystalite > 0) {
                currPlayer.crystalite--;
                crystalite.storeCount++;
                currPlayer.money = currPlayer.money + crystalite.cost;

        } else if (resource.name.equals(energy.name) && currPlayer.energy > 0) {
                currPlayer.energy--;
                energy.storeCount++;
                currPlayer.money = currPlayer.money + energy.cost;
        } else if (resource.name.equals(food.name) && currPlayer.food > 0) {
                currPlayer.food--;
                food.storeCount++;
                currPlayer.money = currPlayer.money + food.cost;

        } else {
            throw new IllegalArgumentException("Not something you can sell");
         }
    }

    public void buyMule() {
        currPlayer.mule++;
        currPlayer.money = currPlayer.money - mule.cost;
    }
    public void setCurrent(Player curr) {
        currPlayer = curr;
    }
}