package Commands;

import java.util.HashMap;
import java.util.List;

public class CommandFactory {

    private static HashMap<String, CommandType> commands;

    public CommandFactory() {
        commands = new HashMap<String, CommandType>();
        fill();
    }

    public enum CommandType {
        LOOK, UNKNOWN
    }

    private void fill() {
        commands.put("look", CommandType.LOOK);
    }

    public CommandType getCommand(String word) {
        CommandType c = commands.get(word);
        if(c == null) c = CommandType.UNKNOWN;
        return c;
    }

    public Command getCommand(CommandType type, List<String> args) {
        return switch (type) {
            case LOOK -> new LOOKcommand(args);
            case UNKNOWN -> new UNKNOWNcommand(args);
        };
    }

}
