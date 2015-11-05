package Model;

public class RandomEvent {

    protected int money;
    protected int food;
    protected int energy;
    protected String message;

    /**
     * constructor
     * @param money the money
     * @param food the food
     * @param energy the energy
     * @param message the message
     */
    public RandomEvent(int money, int food, int energy, String message) {
        this.money = money;
        this.food = food;
        this.energy = energy;
        this.message = message;
    }

    /**
     * getter for money
     * @return int the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * setter for money
     * @param money the money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * getter for food
     * @return int the food
     */
    public int getFood() {
        return food;
    }

    /**
     * setter for food
     * @param food the food
     */
    public void setFood(int food) {
        this.food = food;
    }

    /**
     * getter for energy
     * @return int the energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * setter for energy
     * @param energy the energy
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * getter for meessage
     * @return String message
     */
    public String getMessage() {
        return message;
    }

    /**
     * setter for message
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
