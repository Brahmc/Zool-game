package main;

import main.characters.NonPlayer;
import main.event.Event;
import main.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class Room {
    private final String description;
    private final HashMap<String, Room> exits;
    private final ArrayList<Item> items;
    private final ArrayList<NonPlayer> characters;
    private Event event;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        characters = new ArrayList<>();
        event = null;
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

    /**
     * @param item item to add to room
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * @param item item to removed from room
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * @param name name of item
     */
    public Item getItemByName(String name) {
        for(Item item : items) {
            if(item.getName().equals(name)) return item;
        }
        return null;
    }

    public NonPlayer getCharacterByName(String name) {
        for(NonPlayer character : characters) {
            String cName = character.getName().toLowerCase(Locale.ROOT);
            if(cName.equals(name))
                return character;
        }
        return null;
    }

    public void addCharacter(NonPlayer character) {
        characters.add(character);
    }

    private String getExitString() {
        StringBuilder returnString = new StringBuilder("Exits: ");
        for (String direction : exits.keySet()) {
            returnString.append(" ").append(direction);
        }
        return returnString.toString();
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public String getLongDescription() {
        String info = description + ".\n" + getExitString();
        if(!items.isEmpty()) info += "\n\nThis room has items: " + getItemString();
        if(!characters.isEmpty()) info += "\n\nPeople near you: " + getCharacterString();
        return info;
    }

    private String getItemString() {
        StringBuilder string = new StringBuilder();
        for(Item item : items) {
            string.append("\n").append(item.toString()).append(" ");
        }
        return string.toString();
    }

    private String getCharacterString() {
        HashSet<String> names = new HashSet<>();
        for(NonPlayer c : characters) {
            names.add(c.toString());
        }
        return String.join(", ", names);
    }

    public void characterDie(NonPlayer character) {
        items.addAll(character.getInventory()); // drop all items on the floor
        characters.remove(character);
        characters.add(new NonPlayer(character.getName(), "Dead"));
    }
}
