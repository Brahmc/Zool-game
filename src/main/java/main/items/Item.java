package main.items;

import main.characters.Player;
import main.exceptoins.IllegalItemException;
import main.exceptoins.NoItemException;

public class Item {
    private String name;
    private final String DESCRIPTION;
    private final double WEIGHT;
    private boolean isCollectable;

    public Item(String name, String DESCRIPTION, double weight, boolean isCollectable) {
        this.name = name;
        this.WEIGHT = weight;
        this.DESCRIPTION = DESCRIPTION;
        this.isCollectable = isCollectable;
    }

    public Item(String name, String DESCRIPTION, double weight) {
        this(name, DESCRIPTION, weight, true);
    }

    public String getName() {
        return name;
    }

    public boolean isCollectable() {
        return isCollectable;
    }

    public void setCollectable(boolean collectable) {
        isCollectable = collectable;
    }

    public String toString() {
        return getName() + " (" + DESCRIPTION + ") (" + WEIGHT + "kg)";
    }

    public void use(Player player) throws NoItemException, IllegalItemException {

    }
}
