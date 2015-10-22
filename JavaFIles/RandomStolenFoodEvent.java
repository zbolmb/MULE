public class RandomStolenFoodEvent extends RandomEvent {

    private int money;
    
    public RandomStolenFoodEvent() {
        super("MISCHIEVOUS GLAC-ELVES BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.");
    }
    
    @Override
    protected String generate() {
        Configurations.curPlayer.food *= (.5);
        
        return msg;
    }
}
