package Commands;

import main.exceptoins.NoItemException;
import main.exceptoins.NotCollectableException;
import main.characters.Player;

import java.util.List;

public class TAKEcommand extends Command{
    public TAKEcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
        return "Pick up an item in the current room.";
    }

    @Override
    public boolean execute(Player player) {
        if(getArgs().size() == 0) {
            System.out.println("Take what?");
            return false;
        }
        String itemName = getArgs().get(0);

        try {
            player.takeFromRoom(itemName);
        } catch (NoItemException | NotCollectableException e) {
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("You collected a " + itemName + ".");
        return false;
    }
}
