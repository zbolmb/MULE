public class RandomRelativeMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomRelativeMoneyEvent() {
        super("A DISTANT RELATIVE DIED AND LEFT YOU A VAST FORTUNE. BUT AFTER TAXES YOU ONLY GOT $200.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.money += 200;
        
        return msg;
    }
}
