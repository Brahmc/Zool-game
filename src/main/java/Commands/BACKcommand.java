package Commands;

import main.characters.Player;

import java.util.List;

public class BACKcommand extends Command{
    public BACKcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
        return "Go back to previous room.";
    }

    @Override
    public boolean execute(Player player) {
        if(!player.goBackRoom()) {
            System.out.println("You can't go back any more rooms!!");
        } else {
            System.out.println(player.getInfo());
        }
        return false;
    }
}
