package main.items;

public class ItemWithLevel extends Item{
    private final int level;

    public ItemWithLevel(String name, String description, int level) throws IllegalArgumentException{
        super(name, description);
        if(level < 0 || level > 10) throw new IllegalArgumentException("level has to be between 0 and 20");
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return super.toString() + "(Level: " + level + ")";
    }
}
