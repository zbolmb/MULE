package Model;

public class RandomEvent {

    protected int money;
    protected int food;
    protected int energy;
    protected String message;
    
    public RandomEvent(int money, int food, int energy, String message) {
        this.money = money;
        this.food = food;
        this.energy = energy;
        this.message = message;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
