import Commands.Command;
import Commands.CommandFactory;

import java.util.*;

abstract public class CommandModule {
    private final Scanner reader;
    private final CommandFactory commandFactory;

    public CommandModule() {
        commandFactory = new CommandFactory();
        reader = new Scanner(System.in);
    }

    abstract protected HashSet<CommandFactory.CommandType> getCommands();

    public Command getCommand() {
        String inputLine;
        System.out.print(">");

        inputLine = reader.nextLine();

        String[] lineArray = inputLine.toLowerCase(Locale.ROOT).split("\s+");
        List<String> args = toArgs(lineArray);
        CommandFactory.CommandType commandWord = commandFactory.getCommand(lineArray[0]);

        return toCommand(commandWord, args);
    }

    private List<String> toArgs(String[] lineArray) {
        List<String> args = new LinkedList<>(Arrays.asList(lineArray));  //convert input array to linkedList
        args.remove(0); //remove command word from arguments
        return args;
    }

    protected Command toCommand(CommandFactory.CommandType commandWord, List<String> args) {
        if(getCommands().contains(commandWord)) {
            return commandFactory.getCommand(commandWord, args);
        } else {
            return commandFactory.getCommand(CommandFactory.CommandType.UNKNOWN, args);
        }
    }
}
