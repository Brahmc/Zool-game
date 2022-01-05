package Commands;

import main.characters.Player;

import java.util.List;

public class LOOKcommand extends Command {

    public LOOKcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
        return "Get a description of the current room, see if it contains items or characters.";
    }

    @Override
    public boolean execute(Player player) {
        System.out.println(player.getInfo());
        return false;
    }
}
