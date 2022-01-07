package main.items;

import main.characters.Player;
import main.exceptoins.IllegalItemException;
import main.exceptoins.NoItemException;

public class Weapon extends Item{
    final private int DAMAGE;

    public Weapon(String name, String description, double weight, int damage) {
        super(name, description, weight);
        this.DAMAGE = damage;
    }

    @Override
    public void use(Player player) throws NoItemException, IllegalItemException {
        player.equipWeaponByName(getName());
    }

    public int getDamage() {
        return DAMAGE;
    }
}
