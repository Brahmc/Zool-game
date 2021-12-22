package Commands;

import main.OptionParse;
import main.Parser;
import main.Player;

import java.util.List;

public class TESTcommand extends Command{
    public TESTcommand(List<String> args) {
        super(args);
    }

    @Override
    public boolean execute(Player player) {
        String answer = OptionParse.multipleChoice(new Parser(), "Choose between:", "run away", "stay");

        switch (answer) {
        case "run away" -> System.out.println("Ur a loser");
        case "stay" -> System.out.println("actual chad");
        }
        return false;
    }
}
