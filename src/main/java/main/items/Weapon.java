package main.items;

public class Weapon extends Item{
    final private int DAMAGE;

    public Weapon(String name, String description, double weight, int damage) {
        super(name, description, weight);
        this.DAMAGE = damage;
    }


    public int getDamage() {
        return DAMAGE;
    }
}
