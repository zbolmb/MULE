public class RandomRatMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomRatMoneyEvent() {
        super("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE. YOU EARNED $100.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money += 100;
        
        return msg;
    }
}
