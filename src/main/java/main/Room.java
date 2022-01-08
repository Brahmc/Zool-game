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

    public void addCharacter(NonPlayer character) {
        characters.add(character);
    }

    /**
     * @param event event which gets triggered when player enters the room
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        Event event = this.event;
        this.event = null; // remove event when it gets called
        return event;
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

    /**
     * @param name name of NonPlayer
     */
    public NonPlayer getNonPlayerByName(String name) {
        for(NonPlayer character : characters) {
            String cName = character.getName().toLowerCase(Locale.ROOT);
            if(cName.equals(name))
                return character;
        }
        return null;
    }

    private String getExitString() {
        return "Exits: " + String.join(" ", exits.keySet());
    }

    /**
     * @return rooms description, items, characters
     */
    public String getLongDescription() {
        String info = description + ".\n" + getExitString();
        if(!items.isEmpty()) info += "\n\nThis room has items: " + getItemString();
        if(!characters.isEmpty()) info += "\n\nPeople near you: " + getCharacterString();
        return info;
    }

    /**
     * @return string with list of items in room
     */
    private String getItemString() {
        StringBuilder string = new StringBuilder();
        for(Item item : items) {
            string.append("\n").append(item.toString()).append(" ");
        }
        return string.toString();
    }

    /**
     * @return string with list of characters in room
     */
    private String getCharacterString() {
        HashSet<String> names = new HashSet<>();
        for(NonPlayer c : characters) {
            names.add(c.toString());
        }
        return String.join(", ", names);
    }

    /**
     * Drops all the characters items, replaces the character with a dead version.
     */
    public void characterDie(NonPlayer character) {
        items.addAll(character.getInventory()); // drop all items on the floor
        characters.remove(character);
        characters.add(new NonPlayer(character.getName(), "Dead"));
    }
}
