public class RandomWormMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomWormMoneyEvent() {
        super("THE COLONY QUADRUPLED YOUR MONEY FOR STOPPING THE WART WORM INFESTATION.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money *= 4;
        
        return msg;
    }
}
