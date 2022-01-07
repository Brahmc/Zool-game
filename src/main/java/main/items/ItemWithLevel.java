package main.items;

public class ItemWithLevel extends Item{

    private final int LEVEL;

    public ItemWithLevel(String name, String description, double weight, int level) throws IllegalArgumentException{
        super(name, description, weight);
        if(level < 0 || level > 10) throw new IllegalArgumentException("level has to be between 0 and 20");
        LEVEL = level;
    }

    public int getLevel() {
        return LEVEL;
    }
}
