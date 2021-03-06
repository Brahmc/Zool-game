package main.characters;

import main.DamageInfo;
import main.items.Armor;
import main.items.Item;
import main.items.Weapon;

import java.util.ArrayList;
import java.util.Random;

public abstract class Character {
    private final String name;
    private int color; // color of character's name when mentioned (ANSI values)
    private int health; //all characters start with 100 health
    private Weapon weapon;
    private Armor armor;
    private final Weapon DEFAULT_WEAPON;
    private final Armor DEFAULT_ARMOR;
    private final int MAX_HEALTH;
    private final ArrayList<Item> inventory;
    private final Random rand;

    public Character(String name, int maxHealth) {
        this.name = name;
        color = 0;
        health = maxHealth;
        DEFAULT_WEAPON = new Weapon("fists", "no weapon equipped", 0, 0);
        DEFAULT_ARMOR = new Armor("naked", "no armor equipped", 0);
        MAX_HEALTH = maxHealth;
        inventory = new ArrayList<>();
        rand = new Random();
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
        if(!getInventory().contains(weapon)) getInventory().add(weapon);
        this.weapon = weapon;
    }

    protected Weapon getWeapon() {
        if(weapon == null || !inventory.contains(weapon)) return DEFAULT_WEAPON;
        return weapon;
    }

    protected Armor getArmor() {
        if(armor == null || !inventory.contains(armor)) return DEFAULT_ARMOR;
        return armor;
    }

    public void setArmor(Armor armor) {
        if(!getInventory().contains(armor)) getInventory().add(armor);
        this.armor = armor;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public boolean isDead() {
        return (health <= 0);
    }

    /**
     * Add item to inventory, will change the item's name if different item with the same name
     * is currently in inventory.
     * @param item item that will be added
     */
    public void giveItem(Item item) {
        int numb = 1;
        String name = item.getName().replaceAll("[0-9]",""); // remove numbers
        String newName = name;
        while (getItemByName(newName) != null) {
            numb++;
            newName = name + numb; //change name if multiple items with same name
        }
        item.changeName(newName);
        inventory.add(item);
    }

    /**
     * Give other character an item and remove it from inventory.
     * @param character character item will be given to
     * @param item item that will be given
     * @return string with response of character
     */
    public String giveCharacter(Character character, Item item) {
        if(!getInventory().contains(item)) return "I don't have that item!";

        character.giveItem(item);
        getInventory().remove(item);
        return getDisplayName() + " gave you: " + item;
    }

    /**
     * @param name String matching Item name
     */
    protected Item getItemByName(String name) {
        for(Item item : getInventory()) {
            if(item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Reduces the amount of taken damage depending on current armor.
     * @param amount damage inflicted on character
     */
    public int takeDamage(int amount) throws IllegalArgumentException{
        if(amount < 0) throw new IllegalArgumentException("damage can't be smaller then 0");
        int damageReduction = getArmor().getLevel() * 8; // percentage the amount of damage gets reduced by (depending on armor level)
        int damage = amount * (100 - damageReduction)/100; // amount of damage character takes
        addHealth(-damage);
        return damage;
    }

    public void addHealth(int amount) {
        // make sure health stays between 0 and 100
        health += amount;
        if(health < 0) health = 0;
        if(health > MAX_HEALTH) health = MAX_HEALTH;
    }

    /**
     * Depends on weapon level and citrate (has random element).
     * @return damage character does
     */
    public DamageInfo getDamage() {
        int damageIncrease = getWeapon().getLevel() * 8; // percentage the amount of damage gets increased by (depending on weapon level)
        int critChance = getWeapon().getCritRate() * 8; // percentage for chance player gets critical hit

        int critBonus = 1; // factor damage gets multiplied by on critical hit
        boolean critHit = false;
        if(rand.nextInt(99) < critChance) {
            critBonus = 2; // if hit is critical damage doubles
            critHit = true;
        }
        int damage = 20 * (100 + damageIncrease)/100 * critBonus;
        return new DamageInfo(damage, critHit);
    }

    /**
     * Displays health in green when character has more than 30, if not it gets displayed in red.
     */
    public String getDisplayHealth() {
        if(health < 30) return "\u001B[31m" + health + "\u001B[0m"; // heath will be displayed in red
        return "\u001B[32m" + health + "\u001B[0m"; // health will be displayed in green
    }

    /**
     * @return String with equipped items
     */
    public String getStats() {
        String BOLD = "\033[0;1m"; // Start bold (ANSI)
        String END = "\u001B[0m"; // White text
        return BOLD+"Equipped:"+END + // in bold
                "\nWeapon: " + getWeapon() +
                "\nArmor: " + getArmor();
    }
}
