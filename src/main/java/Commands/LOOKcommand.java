package Commands;

import main.Player;

import java.util.List;

public class LOOKcommand extends Command {

    public LOOKcommand(List<String> args) {
        super(args);
    }

    public boolean execute(Player player) {
        System.out.println(player.getInfo());
        return false;
    }
}
