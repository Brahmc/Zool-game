package Commands;

import java.util.List;

public class UNKNOWNcommand extends Command {

    public UNKNOWNcommand(List<String> args) {
        super(args);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
