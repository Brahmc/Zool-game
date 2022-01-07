package main.characters;

import main.items.Weapon;

public class HostileNonPlayer extends NonPlayer{
    private Weapon weapon;
    private int armor;

    public HostileNonPlayer(String name, Weapon weapon, int armor) {
        super(name);
        this.weapon = weapon;
    }
}
