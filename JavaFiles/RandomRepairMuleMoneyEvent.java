public class RandomRepairMuleMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomRepairMuleMoneyEvent() {
        super("ONE OF YOUR MULES LOST A BOLT. REPAIRS COST YOU $150.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money -= 150;
        
        if (Configurations.curPlayer.money < 0) {
            Configurations.curPlayer.money = 0;
        }
        
        return msg;
    }
}
