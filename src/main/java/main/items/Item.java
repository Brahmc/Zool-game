package main.items;

import main.characters.Player;
import main.exceptoins.IllegalItemException;

public class Item {
    private String name;
    private final String description;
    private final boolean isCollectable;

    public Item(String name, String description, boolean isCollectable) {
        this.name = name;
        this.description = description;
        this.isCollectable = isCollectable;
    }

    public Item(String name, String description) {
        this(name, description, true);
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
        return string;
    }

    /**
     * Performs action on player depending on the item instance
     * @return string specific to instance of item
     * @throws IllegalItemException item can't be used or equipped
     */
    public String use(Player player) throws  IllegalItemException {
        // throws error because default items can't be used
        throw new IllegalItemException("This item can't be used or equipped.");
    }
}
