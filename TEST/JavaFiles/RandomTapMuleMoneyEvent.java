public class RandomTapMuleMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomTapMuleMoneyEvent() {
        super("YOUR MULE WON THE COLONY TAP-DANCING CONTEST. YOU QUADRUPLED YOUR MONEY.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money *= 4;
        
        return msg;
    }
}
