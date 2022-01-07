package Commands;

import main.characters.Player;

import java.util.List;

public class INVcommand extends Command{


    public INVcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
        return "Get a list of items in your inventory with description.";
    }

    @Override
    public boolean execute(Player player) {
        System.out.println(player.getDisplayName() + "'s inventory:");
        System.out.println(player.seeInventory());
        return false;
    }
}
