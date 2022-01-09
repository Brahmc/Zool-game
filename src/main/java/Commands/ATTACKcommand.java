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
        return "Attack the character you are currently fighting with.";
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
        int damageTaken = defender.takeDamage(damage); // damage taken by defender (Armor reduces damage)

        //print result
        String damageString = "(-\u001B[31m" + damageTaken + "\u001B[0m)"; // displays damage in red (ANSI)

        String attackerName, defenderName, defenderNameEnd;
        boolean emptyLine = false;
        if(attacker instanceof Player) {
            attackerName = "You";
            defenderNameEnd = "'s";
            defenderName = defender.getDisplayName();
            emptyLine = true;
        } else {
            attackerName = attacker.getDisplayName();
            defenderNameEnd = "r";
            defenderName = "You";
        }

        if(damageInfo.isCritHit()) {
            System.out.println(attackerName + " got a critical hit on " + defender + " " + damageString);
        } else {
            System.out.println(attackerName + " attacked " + defenderName + " " + damageString);
        }
        System.out.println(defenderName + defenderNameEnd + " current health: " + defender.getDisplayHealth());
        if(emptyLine) System.out.println();
    }
}
