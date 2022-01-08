package Commands;

import main.DamageInfo;
import main.characters.Character;
import main.characters.Player;
import java.util.List;

public class ATTACKcommand extends Command{

    public ATTACKcommand(List<String> args) {
        super(args);
    }
    private Character attacker;
    private Character defender;

    @Override
    public String getDescription() {
        return "Attack ";
    }

    @Override
    public boolean execute(Player player) { // doesn't execute with normal method
        return false;
    }

    public boolean execute(Player player, Character enemy) {
        setAttackAndDefend(player, enemy);
        attack(); // player attacks enemy
        if(enemy.isDead()) return true;

        setAttackAndDefend(enemy, player); // switch roles
        attack(); // enemy attacks back

        return false;
    }

    private void setAttackAndDefend(Character attacker, Character defender) {
        this.attacker = attacker;
        this.defender = defender;
    }


    private void attack() {
        DamageInfo damageInfo = attacker.getDamage();
        int damage = damageInfo.getDamage();
        int damageTaken = defender.takeDamage(damage); // damage taken by character1 (Armor reduces damage)
        String damageString = "(-\u001B[31m" + damageTaken + "\u001B[0m)"; // displays damage in red (ANSI)

        if(attacker instanceof Player) {
            if(damageInfo.isCritHit()) {
                System.out.println("You got a critical hit on " + defender.getDisplayName() + damageString);
            } else {
                System.out.println("You attacked " + defender.getDisplayName() + damageString);
            }
            System.out.println(defender.getName() + "'s health: " + defender.getDisplayHealth());
            System.out.println();
        } else {
            if(damageInfo.isCritHit()) {
                System.out.println(attacker.getDisplayName() + " got a critical hit on you " + damageString);
            } else {
                System.out.println(attacker.getDisplayName() + " attacked you " + damageString);
            }
            System.out.println("Your current health: " + defender.getDisplayHealth());
        }
    }
}
