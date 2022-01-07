package main.characters;

import main.items.Item;
import main.items.Weapon;

import java.util.ArrayList;

public class Character {
    private final String name;
    private int color; // color of character's name when mentioned (ASCII values)
    private double health; //all characters start with 100 health
    private Weapon weapon;
    private final Weapon DEFAULT_WEAPON;
    private final ArrayList<Item> inventory;

    public Character(String name) {
        this.name = name;
        color = 0;
        health = 100;
        DEFAULT_WEAPON = new Weapon("fists", "", 0, 1);
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

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    protected ArrayList<Item> getInventory() {
        return inventory;
    }

    public void giveItem(Item item) {
        inventory.add(item);
    }

    public boolean isDead() {
        return (health < 0);
    }

    public void takeDamage(double amount) {
        health += Math.round(amount*100)/100.; // round up to 2 decimals
        // make sure health stays between 0 and 100
        if(health < 0) health = 0;
        if(health > 100) health = 100;
    }

    protected Weapon getWeapon() {
        if(weapon == null || !inventory.contains(weapon)) return DEFAULT_WEAPON;
        return weapon;
    }

    public String getDisplayHealth() {
        if(health < 30) return "\u001B[31m" + health + "\u001B[0m"; // heath will be displayed in red
        return "\u001B[" + health + "\u001B[32m"; // health will be displayed in green
    }
}
