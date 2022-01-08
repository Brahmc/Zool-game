package Commands;

import main.characters.Player;

import java.util.List;

public class GOcommand extends Command{

    public GOcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
        return "Go to one of the available exits.";
    }

    @Override
    public boolean execute(Player player) {
        if(getArgs().size() == 0) {
            System.out.println("Go where?");
        }else if(!player.goRoom(getArgs().get(0))) {
            System.out.println("That's not a direction you can go!");
        } else {
            System.out.println(player.getInfo());
        }
        return player.triggerRoomEvent();
    }
}
