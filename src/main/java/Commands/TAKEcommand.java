package Commands;

import main.Exceptoins.NoItemException;
import main.Exceptoins.NotCollectableException;
import main.Player;

import java.util.List;

public class TAKEcommand extends Command{

    public TAKEcommand(List<String> args) {
        super(args);
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
