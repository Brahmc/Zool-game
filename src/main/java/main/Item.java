package main;

public class Item {
    private String name;
    private String description;
    private double weight;
    private int damage;
    boolean isCollectable;

    public Item(String name, String description, double weight, int damage, boolean isCollectable) {
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.damage = damage;
        this.isCollectable = isCollectable;
    }

    public Item(String name, String description, double weight, int damage) {
        this(name, description, weight, damage, true);
    }

    public String getName() {
        return name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String toString() {
        return getName() + " (" + description + ") (" + weight + "kg)" + "(" + damage + " dmg)";
    }


}
