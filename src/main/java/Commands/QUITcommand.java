package Commands;

import main.MultipleChoice;
import main.characters.Player;
import main.parser.Parser;

import java.util.List;

public class QUITcommand extends Command{
    public QUITcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
        return "Leave the game.";
    }

    @Override
    public boolean execute(Player player) {
        if(!MultipleChoice.twoChoice(new Parser(), "Are you sure you want to quit?" +
                "\nNone of your progress will be saved!", "yes", "no")) {
            System.out.println(player.getInfo());
            return false;
        }
        System.out.println("Thanks for playing " + player.getDisplayName() + "!");
        return true;
    }
}
