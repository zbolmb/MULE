public class RandomRacesMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomRacesMoneyEvent() {
        super("YOU LOST $200 BETTING ON THE TWO-LEGGED KAZINGA RACES.");
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
