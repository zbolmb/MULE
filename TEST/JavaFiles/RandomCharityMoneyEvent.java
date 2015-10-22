public class RandomCharityMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomCharityMoneyEvent() {
        super("A CHARITY FROM YOUR HOME-WORLD TOOK PITY ON YOU AND SENT $150.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money += 150;
        
        return msg;
    }
}
