package Commands;

import main.characters.Player;

import java.util.List;

public class QUITcommand extends Command{
    public QUITcommand(List<String> args) {
        super(args);
    }

    @Override
    public boolean execute(Player player) {
        System.out.println("Thanks for playing " + player.getName() + "!");
        return true;
    }
}
