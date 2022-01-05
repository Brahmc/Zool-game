package Commands;

import main.characters.Player;

import java.util.List;

public class TESTcommand extends Command{
    public TESTcommand(List<String> args) {
        super(args);
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean execute(Player player) {

        return false;
    }
}
