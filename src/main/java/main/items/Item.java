package main.items;

public class Item {
    private String name;
    private String description;
    private double weight;
    private boolean isCollectable;

    public Item(String name, String description, double weight, boolean isCollectable) {
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.isCollectable = isCollectable;
    }

    public Item(String name, String description, double weight) {
        this(name, description, weight, true);
    }

    public String getName() {
        return name;
    }

    public boolean isCollectable() {
        return isCollectable;
    }

    public void setCollectable(boolean collectable) {
        isCollectable = collectable;
    }

    public String toString() {
        return getName() + " (" + description + ") (" + weight + "kg)";
    }
}
