public class Resource {
    protected String name;
    protected int cost;
    protected int storeCount;

    public Resource(String rName, int rCost) {
        name = rName;
        cost = rCost;
        storeCount = 0;
    }
}
