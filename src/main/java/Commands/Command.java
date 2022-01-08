package Commands;

import main.characters.Player;

import java.util.List;

abstract public class Command {
    private final List<String> args;

    public Command(List<String> args) {
        this.args = args;
    }

    public List<String> getArgs() {
        return args;
    }

    abstract public String getDescription();

    abstract public boolean execute(Player player);
}
