package main.event;

import main.characters.NonPlayer;
import main.characters.Player;

public class StartEndFight extends StartFight{
    public StartEndFight(String message, NonPlayer enemy) {
        super(message, enemy);
    }

    @Override
    public boolean execute(Player player) {
        super.execute(player);
        return true; // endFight always ends game (player wins or dies)
    }
}
