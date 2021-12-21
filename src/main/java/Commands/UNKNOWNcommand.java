package Commands;

import java.util.List;

public class UNKNOWNcommand extends Command {

    public UNKNOWNcommand(List<String> args) {
        super(args);
    }

    @Override
    public boolean execute() {
        System.out.println("I don't know what you mean...");
        return false;
    }
}
