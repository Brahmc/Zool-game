package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    private final String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }


    /**
     * @param direction direction for which to add exit
     * @param neighbor  The exit
     */
    public void addExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @param direction direction for which to get exit
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item getItemByName(String name) {
        for(Item item : items) {
            if(item.getName().equals(name)) return item;
        }
        return null;
    }

    private String getExitString() {
        StringBuilder returnString = new StringBuilder("Exits: ");
        for (String direction : exits.keySet()) {
            returnString.append(" ").append(direction);
        }
        return returnString.toString();
    }

    public String getLongDescription() {
        String info = description + ".\n" + getExitString();
        if(!items.isEmpty()) info += "\n\nThis room has items: ";
        for(Item item : items) {
            info += "\n" + item.toString() + " ";
        }
        return info;
    }
}
