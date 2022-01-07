package main;

import Commands.Command;
import main.characters.Character;
import main.characters.HostileNonPlayer;
import main.characters.Player;

public class FightModule {

    private Player player;
    private Character enemy;
    CommandParse parser;

    public FightModule(Player player, Character hostile) {
        parser = new CommandParse(CommandParse.Type.FIGHT);
        this.player = player;
        this.enemy = hostile;
    }


    /**
     * @return true if player died
     */
    public boolean startFight() {
        boolean fightOver = false;

        while(!fightOver) {
            Command command = parser.getCommand();
            fightOver = command.execute(player);
            if(enemy.isDead()) {
                fightOver = true;
            }
            enemyAttack(); // after each command enemy attacks

            if(player.isDead()) {
                System.out.println("You were killed by " + enemy.getDisplayName() + "!");
                return false;
            }
        }
        return false;
    }

    private void enemyAttack() {

    }

    private void win() {
        System.out.println();
    }
}
