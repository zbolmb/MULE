public class RandomDividendsMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomDividendsMoneyEvent() {
        super("YOUR OFFWORLD INVESTMENTS IN ARTIFICIAL DUMBNESS PAID $300 IN DIVIDENDS.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money += 300;
        
        return msg;
    }
}
