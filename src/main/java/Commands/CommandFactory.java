package Commands;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CommandFactory {

    private static HashMap<String, CommandType> commandMap;

    public CommandFactory() {
        commandMap = new HashMap<>();
        fill();
    }

    public enum CommandType {
        LOOK, HELP, TAKE, DROP, GO, QUIT, TALK, TEST, UNKNOWN
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
            case TAKE -> new TAKEcommand(args);
            case DROP -> new DROPcommand(args);
            case GO -> new GOcommand(args);
            case QUIT -> new QUITcommand(args);
            case TEST -> new TESTcommand(args);
            case TALK -> new TALKcommand(args);
        };
    }

}
