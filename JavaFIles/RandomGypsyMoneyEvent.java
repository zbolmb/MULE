public class RandomGypsyMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomGypsyMoneyEvent() {
        super("YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $300 TO CLEAN IT UP.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money -= 300;
        
        if (Configurations.curPlayer.money < 0) {
            Configurations.curPlayer.money = 0;
        }
        
        return msg;
    }
}
