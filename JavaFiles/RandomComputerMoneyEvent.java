public class RandomComputerMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomComputerMoneyEvent() {
        super("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $400.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money += 400;
        
        return msg;
    }
}
