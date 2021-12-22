package Commands;

import main.Player;

import java.util.List;

public class GOcommand extends Command{


    public GOcommand(List<String> args) {
        super(args);
    }

    @Override
    public boolean execute(Player player) {
        if(getArgs().size() == 0) {
            System.out.println("Go where?");
        }
        if(!player.goRoom(getArgs().get(0))) {
            System.out.println("That's not a direction you can go!");
        } else {
            System.out.println(player.getInfo());
        }
        return false;
    }
}
