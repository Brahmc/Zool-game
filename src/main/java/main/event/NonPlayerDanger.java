package main.event;

import main.FightModule;
import main.OptionParse;
import main.Parser;
import main.characters.Character;
import main.characters.HostileNonPlayer;
import main.characters.Player;

public class NonPlayerDanger extends Event{

    private Player player;
    private Character hostile;


    public NonPlayerDanger(String message, Player player, Character hostile) {
        super(message);
        this.player = player;
        this.hostile = hostile;
    }

    @Override
    public boolean execute() {
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
