package Commands;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CommandFactory {

    private static HashMap<String, Command> commandMap;

    public CommandFactory() {
        commandMap = new HashMap<>();
        fill();
    }

    public enum Command {
        LOOK, HELP, TAKE, DROP, GO, QUIT, TALK, INV, TEST, UNKNOWN
    }

    private void fill() {   // command words have the same key as the type, this is not a necessity (different languages can easily be added)
        for(Command t : Command.values()) {
            commandMap.put(t.toString().toLowerCase(), t);
        }
    }

    public static HashMap<String, Command> getCommandMap() { //commandMap is always the same
        return commandMap;
    }

    public Command getCommand(String word) {
        Command c = commandMap.get(word);
        if(c == null) return Command.UNKNOWN;
        return c;
    }

    public Commands.Command getCommand(Command type, List<String> args, HashSet<Command> commands) {
        return switch (type) {
            case LOOK -> new LOOKcommand(args);
            case UNKNOWN -> new UNKNOWNcommand(args);
            case HELP -> new HELPcommand(args, commands);
            case TAKE -> new TAKEcommand(args);
            case DROP -> new DROPcommand(args);
            case GO -> new GOcommand(args);
            case QUIT -> new QUITcommand(args);
            case TEST -> new TESTcommand(args);
            case TALK -> new TALKcommand(args);
            case INV -> new INVcommand(args);
        };
    }
}
