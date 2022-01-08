package main.items;

import main.characters.Player;

public class Weapon extends ItemWithLevel{

    private int critRate; // more critRate means critical hits are more likeley

    public Weapon(String name, String description, double weight, int level, int critRate) throws IllegalArgumentException {
        super(name, description, weight, level);
        this.critRate = critRate;
    }

    public int getCritRate() {
        return critRate;
    }

    @Override
    public String toString() {
        return super.toString() + "(Crit Rate: " + critRate + ")";
    }

    @Override
    public String use(Player player)  {
        player.setWeapon(this);
        return "You equipped " + this + ".";
    }
}