package main;

import java.util.ArrayList;

public class Character {
    private final String name;
    private int health;
    private final ArrayList<Item> inventory;

    public Character(String name) {
        this.name = name;
        health = 100;
        inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
