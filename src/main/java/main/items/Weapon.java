package main.items;

import main.characters.Player;
import main.exceptoins.IllegalItemException;
import main.exceptoins.NoItemException;

public class Weapon extends ItemWithLevel{

    public Weapon(String name, String description, double weight, int level) throws IllegalArgumentException {
        super(name, description, weight, level);
    }

    @Override
    public void use(Player player) throws NoItemException, IllegalItemException {
        super.use(player);
        player.equipItem(this);
    }
}
