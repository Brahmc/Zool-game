package Commands;

import main.FightModule;
import main.characters.NonPlayer;
import main.characters.Player;

import java.util.List;

public class FIGHTcommand extends Command{
    public FIGHTcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
        return "Fight a character.";
    }

    @Override
    public boolean execute(Player player) {
        if(getArgs().size() == 0) {
            System.out.println("Fight who?");
            return false;
        }
        NonPlayer nonPlayer = player.getNearbyCharacterByName(getArgs().get(0));
        if(nonPlayer == null) {
            System.out.println("There is no such character in the room!");
            return false;
        }
        if(!nonPlayer.canFight()) {
            System.out.println("You can't fight that character!");
            return false;
        }

        FightModule fight = new FightModule(player, nonPlayer);
        return fight.startFight();
    }
}
