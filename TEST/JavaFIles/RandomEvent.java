public abstract class RandomEvent {
    
    protected String msg;
    
    public RandomEvent(String name) {
        this.msg = msg;
    }
    
    protected abstract String generate();
}
