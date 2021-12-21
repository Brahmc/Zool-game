package Commands;

import main.Parser;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CommandFactory {

    private static HashMap<String, CommandType> commandMap;

    public CommandFactory() {
        commandMap = new HashMap<String, CommandType>();
        fill();
    }

    public enum CommandType {
        LOOK, HELP, UNKNOWN
    }

    private void fill() {   // command words have the same key as the type, this is not a necessity (different languages can be easily added)
        for(CommandType t : CommandType.values()) {
            commandMap.put(t.toString().toLowerCase(), t);
        }
    }

    public CommandType getCommand(String word) {
        CommandType c = commandMap.get(word);
        if(c == null) c = CommandType.UNKNOWN;
        return c;
    }

    public Command getCommand(CommandType type, List<String> args, HashSet<CommandType> commands) {
        return switch (type) {
            case LOOK -> new LOOKcommand(args);
            case UNKNOWN -> new UNKNOWNcommand(args);
            case HELP -> new HELPcommand(args, commands, commandMap);
        };
    }

}
