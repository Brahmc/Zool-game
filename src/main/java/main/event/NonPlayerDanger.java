package main.event;

import main.FightModule;
import main.OptionParse;
import main.Parser;
import main.characters.NonPlayer;
import main.characters.Player;

public class NonPlayerDanger extends Event{

    private NonPlayer hostile;


    public NonPlayerDanger(String message, NonPlayer hostile) {
        super(message);
        this.hostile = hostile;
    }

    @Override
    public boolean execute(Player player) {
        Parser pars = new Parser();
        if(OptionParse.twoChoice(pars, "", "intervene", "back")) {
            FightModule fight = new FightModule(player, hostile);
            return !fight.startFight();
        } else {
            player.goBackRoom();
            System.out.println();
            System.out.println(player.getInfo());
            return false;
        }
    }
}
