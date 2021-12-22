package Commands;

import main.Player;

import java.util.List;

abstract public class Command {
    private List<String> args;

    public Command(List<String> args) {
        this.args = args;
    }

    public List<String> getArgs() {
        return args;
    }

    abstract public boolean execute(Player player);
}
