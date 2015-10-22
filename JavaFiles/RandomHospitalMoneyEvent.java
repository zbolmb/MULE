public class RandomHospitalMoneyEvent extends RandomEvent {

    private int money;
    
    public RandomHospitalMoneyEvent() {
        super("YOUR CHILD WAS BITTEN BY A BAT LIZARD AND THE HOSPITAL BILL COST YOU $200.");
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
