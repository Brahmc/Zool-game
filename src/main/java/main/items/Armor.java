package main.items;

import main.characters.Player;

public class Armor extends ItemWithLevel{


    public Armor(String name, String description, double weight, int level) throws IllegalArgumentException {
        super(name, description, weight, level);
    }

    @Override
    public String use(Player player) {
        player.setArmor(this);
        return "You equipped " + this + ".";
    }
}
