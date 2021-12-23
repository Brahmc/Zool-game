package main;

import java.util.ArrayList;

public class Character {
    private final String name;
    private int color; // color of character's name when mentioned (ASCII values)
    private int health;
    private final ArrayList<Item> inventory;

    public Character(String name) {
        this.name = name;
        color = 0;
        health = 100;
        inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getDisplayName() {
        String colorString = "\u001B[" + color + "m";
        return colorString + name + "\u001B[0m";
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
