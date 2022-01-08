package main.items;

import main.characters.Player;

public class HealingItem extends  Item{
    private final int healAmount;

    public HealingItem(String name, String DESCRIPTION, int healAmount) {
        super(name, DESCRIPTION);
        this.healAmount = healAmount;
    }

    @Override
    public String use(Player player) {
        player.addHealth(healAmount); // heal player
        player.getInventory().remove(this);

        return "You used " + this + " (\u001B[32m+" + healAmount +"\u001B[0m)" + // amount displayed in green (ANSI)
                "\nYour current health: " + player.getDisplayHealth();
    }
}
