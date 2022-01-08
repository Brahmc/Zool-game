package main;

import Commands.ATTACKcommand;
import Commands.Command;
import main.characters.Character;
import main.characters.Player;

public class FightModule {

    private final Player player;
    private final Character enemy;
    CommandParse parser;

    public FightModule(Player player, Character hostile) {
        parser = new CommandParse(CommandParse.Type.FIGHT);
        this.player = player;
        this.enemy = hostile;
    }

    /**
     * @return true if player died or quit
     */
    public boolean startFight() {
        boolean fightOver = false;

        while(!fightOver) {
            Command command = parser.getCommand();
            if(command instanceof ATTACKcommand a) a.execute(player, enemy);
            fightOver = command.execute(player);
            if(enemy.isDead()) {
                fightOver = true;
            }
            enemyAttack(); // after each command enemy attacks

            if(player.isDead()) {
                System.out.println("You were killed by " + enemy.getDisplayName() + "!");
                return true;
            }
        }
        return false;
    }

    private void printStartFight() {
        System.out.println("You are fighting " + enemy.getDisplayName());
        System.out.println("Enemy stats: ");
        System.out.println(enemy.getStats());
    }

    private void enemyAttack() {
        DamageInfo damageInfo = enemy.getDamage();
        int damage = damageInfo.getDamage();
        int damageTaken = player.takeDamage(damage); // damage taken by player (Armor reduces damage)
        String damageString = "\u001B[31m" + damageTaken + "\u001B[0m"; // displays damage in red (ANSI)
        if(damageInfo.isCritHit()) {
            System.out.println(enemy.getDisplayName() + " got a critical hit on you -" + damageString);
        } else {
            System.out.println(enemy.getDisplayName() + "attacked you -" + damageString);
        }
        System.out.println("Your current health: " + player.getDisplayHealth());
    }

    private void printAttack() {

    }

    private void win() {
        System.out.println();
    }
}
