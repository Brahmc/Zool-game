package Commands;

import main.characters.Player;

import java.util.List;

public class INVcommand extends Command{
    public INVcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
            return "Get a list of items in your inventory, equipped items and player health.";
    }

    @Override
    public boolean execute(Player player) {
        System.out.println(player.getDisplayName() + "'s inventory:");
        String inv = player.seeInventory();
        String invDisplay = "\t" + inv.replaceAll("\n", "\n\t"); // shifts text 1 tab to the right
        System.out.println(invDisplay);
        return false;
    }
}
