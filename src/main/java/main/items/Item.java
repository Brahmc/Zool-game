package main.items;

import main.characters.Player;
import main.exceptoins.IllegalItemException;

public class Item {
    private String name;
    private final String description;
    private final double WEIGHT;
    private final boolean isCollectable;

    public Item(String name, String DESCRIPTION, double weight, boolean isCollectable) {
        this.name = name;
        this.WEIGHT = weight;
        this.description = DESCRIPTION;
        this.isCollectable = isCollectable;
    }

    public Item(String name, String DESCRIPTION, double weight) {
        this(name, DESCRIPTION, weight, true);
    }

    public void changeName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isCollectable() {
        return isCollectable;
    }

    public String toString() {
        String string = getName();
        if(!description.isEmpty()) string +=  " (" + description + ")";
        if(WEIGHT != 0 ) string += " (" + WEIGHT + "kg)";
        return string;
    }

    public String use(Player player) throws  IllegalItemException {
        throw new IllegalItemException("This item can't be used or equipped."); // throws error because default items can't be used
    }
}
