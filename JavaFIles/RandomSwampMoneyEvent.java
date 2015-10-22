public class RandomSwampMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomSwampMoneyEvent() {
        super("YOU WON THE COLONY SWAMP EEL EATING CONTEST AND DOUBLED YOUR MONEY. (YUCK!)");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money *= 2;
        
        return msg;
    }
}
