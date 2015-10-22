public class RandomRoofMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomRoofMoneyEvent() {
        super("FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $200.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money -= 200;
        
        if (Configurations.curPlayer.money < 0) {
            Configurations.curPlayer.money = 0;
        }
        
        return msg;
    }
}
