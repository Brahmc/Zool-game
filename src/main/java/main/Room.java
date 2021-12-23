package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class Room {
    private final String description;
    private final HashMap<String, Room> exits;
    private final ArrayList<Item> items;
    private final ArrayList<Character> characters;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        characters = new ArrayList<>();
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

    public Character getCharacterByName(String name) {
        for(Character character : characters) {
            String cName = character.getName().toLowerCase(Locale.ROOT);
            if(cName.equals(name))
                return character;
        }
        return null;
    }

    public void addCharacter(Character character) {
        characters.add(character);
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
        for(Character c : characters) {
            names.add(c.getDisplayName());
        }
        return String.join(", ", names);
    }
}
