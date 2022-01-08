package main.event;

import main.FightModule;
import main.characters.NonPlayer;
import main.characters.Player;

public class StartFight extends Event{
    private final NonPlayer ENEMY;

    public StartFight(String message, NonPlayer enemy) {
        super(message);
        this.ENEMY = enemy;
    }

    @Override
    public boolean execute(Player player) {
        System.out.println();
        System.out.println(getMessage());

        FightModule fightModule =new FightModule(player, ENEMY);
        return fightModule.startFight();
    }
}
