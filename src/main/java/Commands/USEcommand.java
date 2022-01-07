package Commands;

import main.characters.Player;

import java.util.List;

public class USEcommand extends Command{


    public USEcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
        return "Use an item, equip a weapon or armor.";
    }

    @Override
    public boolean execute(Player player) {
        List<String> args = getArgs();
        if(getArgs().size() == 0) {
            System.out.println("Use what?");
        }
        try {
            player.useItemByName(args.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
