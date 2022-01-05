package Commands;

import main.characters.Player;

import java.util.List;

public class DROPcommand extends Command {


    public DROPcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
        return "Drops an item in the current room.";
    }

    @Override
    public boolean execute(Player player) {
        if(getArgs().size() == 0) {
            System.out.println("Drop what?");
            return false;
        }
        String itemName = String.join(" ", getArgs()); // items can have spaces in them

        if (player.drop(itemName)) {
            System.out.println(player.getName() + " dropped " + itemName);
        } else {
            System.out.println(player.getName() + " does not have that item.");
        }
        return false;
    }
}
