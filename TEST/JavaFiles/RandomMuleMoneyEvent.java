public class RandomMuleMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomMuleMoneyEvent() {
        super("YOUR MULE WAS JUDGED 'BEST BUILT' AT THE COLONY FAIR. YOU WON $100.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money += 100;
        
        return msg;
    }
}
