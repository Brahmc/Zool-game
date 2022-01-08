package main;

import Commands.ATTACKcommand;
import Commands.Command;
import main.characters.NonPlayer;
import main.characters.Player;

public class FightModule {

    private final Player player;
    private final NonPlayer enemy;
    CommandParse parser;

    public FightModule(Player player, NonPlayer hostile) {
        parser = new CommandParse(CommandParse.Type.FIGHT);
        this.player = player;
        this.enemy = hostile;
    }

    /**
     * @return true if player died or quit
     */
    public boolean startFight() {
        printStartFight();
        boolean fightOver = false;

        while(!fightOver) {
            Command command = parser.getCommand();
            if(command instanceof ATTACKcommand a) fightOver = a.execute(player, enemy);
            else fightOver = command.execute(player);

            if(player.isDead()) {
                System.out.println("You were killed by " + enemy.getDisplayName() + "!");
                return true;
            }
        }

        endFight();
        return false;
    }

    private void printStartFight() {
        System.out.println("You are fighting " + enemy);
        System.out.println(enemy.getDisplayName() + " 's stats:");
        System.out.println(enemy.getStats().replaceAll("\n", "\n\t"));
    }

    private void endFight() {
        System.out.println("You killed " + enemy);
        player.getCurrentRoom().characterDie(enemy);

        System.out.println(player.getInfo());
    }

    private void printAttack() {

    }

    private void win() {
        System.out.println();
    }
}
